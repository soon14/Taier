package com.dtstack.rdos.engine.execution.base;

import com.dtstack.rdos.commom.exception.RdosException;
import com.dtstack.rdos.common.util.PublicUtil;
import com.dtstack.rdos.engine.execution.base.operator.Operator;
import com.dtstack.rdos.engine.execution.base.pojo.JobResult;
import com.dtstack.rdos.engine.execution.base.pojo.ParamAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dtstack.rdos.engine.execution.base.components.OrderObject;
import com.dtstack.rdos.engine.execution.base.enumeration.ComputeType;
import com.dtstack.rdos.engine.execution.base.enumeration.EJobType;
import com.dtstack.rdos.engine.execution.base.enumeration.RdosTaskStatus;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Reason:
 * Date: 2017/2/21
 * Company: www.dtstack.com
 * @ahthor xuchao
 */

public class JobClient extends OrderObject{

    private static final Logger logger = LoggerFactory.getLogger(JobClient.class);

    private JobClientCallBack jobClientCallBack;

    private List<Operator> operators;

    private Properties confProperties;

    private String sql;

    private String taskParams;

    private String jobName;

    private String taskId;

    private String engineTaskId;

    private EJobType jobType;

    private ComputeType computeType;

    private String engineType;

    private JobResult jobResult;

    /**externalPath 不为null则为从保存点恢复*/
    private String externalPath;

    /**提交MR执行的时候附带的执行参数*/
    private String classArgs;

    private int again = 1;

    private String groupName;
    

    /***
     * 获取engine上job执行的状态
     * @param engineTaskId engine jobId
     * @return
     */
    public static RdosTaskStatus getStatus(String engineType, String engineTaskId) {
        return ClientOperator.getInstance().getJobStatus(engineType, engineTaskId);
    }

    public static String getEngineLog(String engineType, String jobId){
        return ClientOperator.getInstance().getEngineLogByHttp(engineType, jobId);
    }

    public static String getInfoByHttp(String engineType, String path){
        return ClientOperator.getInstance().getEngineMessageByHttp(engineType, path);
    }


    public JobClient() {

    }

    public JobClient(ParamAction paramAction) throws Exception {
        this.sql = paramAction.getSqlText();
        this.taskParams = paramAction.getTaskParams();
        this.jobName = paramAction.getName();
        this.taskId = paramAction.getTaskId();
        this.engineTaskId = paramAction.getEngineTaskId();
        this.jobType = EJobType.getEJobType(paramAction.getTaskType());
        this.computeType = ComputeType.getType(paramAction.getComputeType());
        this.externalPath = paramAction.getExternalPath();
        this.engineType = paramAction.getEngineType();
        this.classArgs = paramAction.getExeArgs();
        if(taskParams != null){
            this.confProperties = PublicUtil.stringToProperties(taskParams);
        }

        //将任务id 标识为对象id
        this.id = taskId;

    }

    public ParamAction getParamAction(){
        ParamAction action = new ParamAction();
        action.setSqlText(sql);
        action.setTaskParams(taskParams);
        action.setName(jobName);
        action.setTaskId(taskId);
        action.setEngineTaskId(engineTaskId);
        action.setTaskType(jobType.getType());
        action.setComputeType(computeType.getType());
        action.setExternalPath(externalPath);
        action.setEngineType(engineType);
        action.setExeArgs(classArgs);
        return action;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEngineTaskId() {
        return engineTaskId;
    }

    public void setEngineTaskId(String engineTaskId) {
        this.engineTaskId = engineTaskId;
    }

    public void addOperators(List<Operator> operators) {
        this.operators.addAll(operators);
    }

    public void addOperator(Operator operator) {
        operators.add(operator);
    }

    public List<Operator> getOperators() {
        return operators;
    }


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public EJobType getJobType() {
        return jobType;
    }

    public void setJobType(EJobType jobType) {
        this.jobType = jobType;
    }

    public JobResult getJobResult() {
        return jobResult;
    }

    public void setJobResult(JobResult jobResult) {
        this.jobResult = jobResult;
    }

    public ComputeType getComputeType() {
        return computeType;
    }

    public void setComputeType(ComputeType computeType) {
        this.computeType = computeType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Properties getConfProperties() {
        return confProperties;
    }

    public void doJobClientCallBack(Map<String, ? extends Object> param){
        if(jobClientCallBack == null){
            throw new RdosException("not set jobClientCallBak...");
        }

        jobClientCallBack.execute(param);
    }

    public void setJobClientCallBack(JobClientCallBack jobClientCallBack) {
        this.jobClientCallBack = jobClientCallBack;
    }

    public String getSql() {
        return sql;
    }

    public String getTaskParams() {
        return taskParams;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public String getClassArgs() {
        return classArgs;
    }

    public void setClassArgs(String classArgs) {
        this.classArgs = classArgs;
    }

	public void submitJob() throws Exception {
		JobSubmitExecutor.getInstance().submitJob(this);
	}

	public void stopJob() throws Exception {
        JobSubmitExecutor.getInstance().stopJob(this);
	}

	public int getAgain() {
		return again;
	}

	public void setAgain(int again) {
		this.again = again;
	}

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setTaskParams(String taskParams) {
        this.taskParams = taskParams;
    }

    public String getExternalPath() {
        return externalPath;
    }

    public void setExternalPath(String externalPath) {
        this.externalPath = externalPath;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "JobClient{" +
                "jobClientCallBack=" + jobClientCallBack +
                ", operators=" + operators +
                ", confProperties=" + confProperties +
                ", sql='" + sql +
                ", taskParams='" + taskParams +
                ", jobName='" + jobName+
                ", taskId='" + taskId +
                ", engineTaskId='" + engineTaskId +
                ", jobType=" + jobType +
                ", computeType=" + computeType +
                ", engineType='" + engineType +
                ", jobResult=" + jobResult +
                ", externalPath=" + externalPath +
                ", classArgs='" + classArgs +
                ", again=" + again +
                ", groupName=" + groupName +
                '}';
    }
}
