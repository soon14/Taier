package com.dtstack.rdos.engine.execution.queue;

import com.dtstack.rdos.commom.exception.RdosException;
import com.dtstack.rdos.common.config.ConfigParse;
import com.dtstack.rdos.engine.execution.base.JobClient;
import com.dtstack.rdos.engine.execution.base.enumeration.EngineType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 管理任务执行队列
 * 当groupExeQueue为空之后回收
 * Date: 2018/1/11
 * Company: www.dtstack.com
 * @author xuchao
 */

public class ExeQueueMgr {

    private static final Logger LOG = LoggerFactory.getLogger(ExeQueueMgr.class);

    /**所有集群的队列信息*/
    private ClusterQueueZKInfo clusterQueueInfo;

    private Map<String, EngineTypeQueue> engineTypeQueueMap = Maps.newConcurrentMap();

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private static ExeQueueMgr exeQueueMgr = new ExeQueueMgr();

    private ExeQueueMgr(){

        //根据配置的引擎类型初始化engineTypeQueueMap
        List<String> typeList = Lists.newArrayList();
        ConfigParse.getEngineTypeList().forEach( info ->{
            String clientTypeStr = (String) info.get(ConfigParse.TYPE_NAME_KEY);
            clientTypeStr = EngineType.getEngineTypeWithoutVersion(clientTypeStr);
            typeList.add(clientTypeStr);
            engineTypeQueueMap.put(clientTypeStr, new EngineTypeQueue(clientTypeStr));
        });

        executorService.submit(new TimerClear(typeList));
    }

    public static ExeQueueMgr getInstance(){
        return exeQueueMgr;
    }

    public void add(JobClient jobClient) throws InterruptedException {
        EngineTypeQueue engineTypeQueue = engineTypeQueueMap.get(jobClient.getEngineType());
        if(engineTypeQueue == null){
            throw new RdosException("not support engineType:" + jobClient.getEngineType());
        }

        engineTypeQueue.add(jobClient);
    }

    public boolean remove(String engineType, String groupName, String taskId){
        EngineTypeQueue engineTypeQueue = engineTypeQueueMap.get(engineType);
        if(engineTypeQueue == null){
            throw new RdosException("not support engineType:" + engineType);
        }

        return engineTypeQueue.remove(groupName, taskId);
    }


    /**
     * zk监听模块调用该接口--更新本地的集群队列信息缓存
     * @param clusterQueueInfo
     */
    public void updateZkGroupPriorityInfo(Map<String, Map<String, Map<String, Integer>>> clusterQueueInfo){
        this.clusterQueueInfo = new ClusterQueueZKInfo(clusterQueueInfo);
    }

    /**
     * 获取当前节点的队列信息
     */
    public Map<String, Map<String, Integer>> getZkGroupPriorityInfo(){
        Map<String, Map<String, Integer>> result = Maps.newHashMap();
        engineTypeQueueMap.forEach((engineType, queue) -> result.put(engineType, queue.getZkGroupPriorityInfo()));
        return result;
    }


    public boolean checkCanAddToWaitQueue(String engineType, String groupName){
        if(engineType == null){
            return false;
        }

        EngineTypeQueue engineTypeQueue = engineTypeQueueMap.get(engineType);
        if(engineTypeQueue == null){
            throw new RdosException("not support engineType:" + engineType);
        }

        return engineTypeQueue.checkCanAddToWaitQueue(groupName);
    }

    public boolean checkLocalPriorityIsMax(String engineType, String groupName, String localAddress) {
        if(clusterQueueInfo == null){
            //等待第一次从zk上获取信息
            return false;
        }

        ClusterQueueZKInfo.EngineTypeQueueZKInfo zkInfo = clusterQueueInfo.getEngineTypeQueueZkInfo(engineType);
        if(zkInfo == null){
            return true;
        }

        EngineTypeQueue engineTypeQueue = engineTypeQueueMap.get(engineType);
        if(engineTypeQueue == null){
            throw new RdosException("not support engineType:" + engineType);
        }

        return engineTypeQueue.checkLocalPriorityIsMax(groupName, localAddress, zkInfo);
    }

    public Map<String, EngineTypeQueue> getEngineTypeQueueMap() {
        return engineTypeQueueMap;
    }


    class TimerClear implements Runnable{

        /**连续3次检查队列为空则回收*/
        private static final int FAILURE_RATE = 3;

        /**5s 检查一次队列*/
        private static final long CHECK_INTERVAL = 5 * 1000;

        /**TODO 调整成对象*/
        private Map<String, Map<String, Integer>> cache = Maps.newHashMap();

        public TimerClear(List<String> engineTypeList){
            engineTypeList.forEach(type -> cache.put(type, Maps.newHashMap()));
        }

        @Override
        public void run() {

            LOG.info("timer clear start up...");

            while (true){

                try{
                    engineTypeQueueMap.forEach((engineType, engineTypeQueue) -> {
                        Map<String, Integer> engineTypeCache = cache.get(engineType);
                        engineTypeQueue.getGroupExeQueueMap().forEach((name, queue) ->{
                            int currVal = 0;
                            if(queue == null || queue.size() == 0){
                                currVal = engineTypeCache.getOrDefault(name, 0);
                                currVal++;
                            }

                            engineTypeCache.put(name, currVal);

                            //清理空的队列
                            Iterator<Map.Entry<String, Integer>> iterator = engineTypeCache.entrySet().iterator();
                            for( ;iterator.hasNext(); ){
                                Map.Entry<String, Integer> entry = iterator.next();

                                if(entry.getValue() >= FAILURE_RATE){
                                    String groupName = entry.getKey();
                                    engineTypeQueue.remove(groupName);
                                    iterator.remove();
                                }
                            }
                        });

                    });
                }catch (Throwable t){
                    LOG.error("", t);
                }finally {
                    try {
                        Thread.sleep(CHECK_INTERVAL);
                    } catch (InterruptedException e) {
                        LOG.error("", e);
                    }
                }
            }
        }
    }

}
