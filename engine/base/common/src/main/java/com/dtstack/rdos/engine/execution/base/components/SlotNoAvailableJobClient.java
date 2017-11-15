package com.dtstack.rdos.engine.execution.base.components;

import com.dtstack.rdos.engine.execution.base.JobClient;
import com.dtstack.rdos.engine.execution.base.JobSubmitExecutor;
import com.dtstack.rdos.engine.execution.base.enumeration.EngineType;
import com.dtstack.rdos.engine.execution.base.util.EngineRestParseUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author sishu.yss
 *
 */
public class SlotNoAvailableJobClient {
	
	private Logger logger =LoggerFactory.getLogger(SlotNoAvailableJobClient.class);
	
	private ReentrantLock reentrantLock = new ReentrantLock();
	
	private volatile Map<String,JobClient> slotNoAvailableJobClients = Maps.newLinkedHashMap();
	
	public void noAvailSlotsJobaddExecutionQueue(OrderLinkedBlockingQueue<OrderObject> orderLinkedBlockingQueue){
		try{
			reentrantLock.lock();
			Iterator<String> iterator =  slotNoAvailableJobClients.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				JobClient job = slotNoAvailableJobClients.get(key);
				if(StringUtils.isBlank(job.getEngineTaskId()) && !isSubmitFail(job)){
					logger.info("------ job: {} add into orderLinkedBlockingQueue again.", job.getTaskId());
					orderLinkedBlockingQueue.put(job);
					iterator.remove();
				} else if(StringUtils.isBlank(job.getEngineTaskId()) && isSubmitFail(job)){

                    //如果遇到由于集群不可用的情况下需要重复提交
                    if(isSubmitFailOfEngineDown(job)){
                        orderLinkedBlockingQueue.put(job);
                        iterator.remove();
                        continue;
                    }

                    //其他提交失败的直接移除
                    iterator.remove();
                }else {
					if(JobSubmitExecutor.getInstance().judgeSlostsAndAgainExecute(job.getEngineType(),job.getEngineTaskId())){
						orderLinkedBlockingQueue.put(job);
						iterator.remove();
					}else{
						iterator.remove();
					}
				}
			}
            
		}catch(Throwable e){
			logger.error("",e);
		}finally{
			reentrantLock.unlock();
		}
	}

	public boolean isSubmitFail(JobClient jobClient){

	    if(jobClient.getJobResult() != null && jobClient.getJobResult().isErr()){
            return true;
        }

        return false;
    }

    public boolean isSubmitFailOfEngineDown(JobClient jobClient){

	    try{
	        if(EngineType.isFlink(jobClient.getEngineType())){
                return EngineRestParseUtil.FlinkRestParseUtil.checkFailureForEngineDown(jobClient.getJobResult().getMsgInfo());
            }else if(EngineType.isSpark(jobClient.getEngineType())){
                return EngineRestParseUtil.SparkRestParseUtil.checkFailureForEngineDown(jobClient.getJobResult().getMsgInfo());
            }else{
                return false;
            }

        }catch (Exception e){
	        logger.error("", e);
        }

        return false;

    }
	
	public void put(JobClient jobClient){
		try{
			reentrantLock.lock();
			this.slotNoAvailableJobClients.put(jobClient.getTaskId(), jobClient);
		}catch(Throwable e){
			logger.error("",e);
		}finally{
			reentrantLock.unlock();
		}
	}
	
	public boolean remove(String jobId){
		try{
			reentrantLock.lock();
			return slotNoAvailableJobClients.remove(jobId,slotNoAvailableJobClients.get(jobId));
		}catch(Throwable e){
			logger.error("",e);
		}finally{
			reentrantLock.unlock();
		}
		return false;
	}
}
