package com.dtstack.rdos.engine.entrance.db.dataobject;

/**
 * Reason:
 * Date: 2017/3/7
 * Company: www.dtstack.com
 *
 * @ahthor xuchao
 */

public class RdosServerLog extends DataObject{

    private String taskId;

    private String logInfo;
    
    private Long actionLogId;
    
    public Long getActionLogId() {
		return actionLogId;
	}

	public void setActionLogId(Long actionLogId) {
		this.actionLogId = actionLogId;
	}

	public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
}
