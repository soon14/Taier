package com.dtstack.batch.vo.schedule;

import com.dtstack.batch.vo.base.PageVO;
import com.dtstack.batch.vo.fill.QueryFillDataJobListVO;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: dazhi
 * @Date: 2021/12/6 3:42 PM
 * @Email:dazhi@dtstack.com
 * @Description:
 */
public class QueryTaskListVO extends PageVO {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryFillDataJobListVO.class);

    /**
     * 租户
     */
    @ApiModelProperty(value = "租户id", hidden = true,required = true)
    private Long tenantId;

    /**
     * 所属用户
     */
    @ApiModelProperty(value = "所属用户")
    private Long ownerId;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String name;

    /**
     * 最近修改的开始时间
     */
    @ApiModelProperty(value = "最近修改的开始时间 单位毫秒")
    private Long startModifiedTime;

    /**
     * 最近修改的结束时间
     */
    @ApiModelProperty(value = "最近修改的结束时间 单位毫秒")
    private Long endModifiedTime;


    /**
     * 调度状态：0 正常 1冻结 2停止
     */
    @ApiModelProperty(value = "调度状态：0 正常 1冻结 2停止", example = "0")
    private Integer scheduleStatus;

    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    private List<Integer> taskTypeList;

    /**
     * 周期类型
     */
    @ApiModelProperty(value = "周期类型", hidden = true)
    private List<Integer> periodTypeList;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getStartModifiedTime() {
        return startModifiedTime;
    }

    public void setStartModifiedTime(Long startModifiedTime) {
        this.startModifiedTime = startModifiedTime;
    }

    public Long getEndModifiedTime() {
        return endModifiedTime;
    }

    public void setEndModifiedTime(Long endModifiedTime) {
        this.endModifiedTime = endModifiedTime;
    }

    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public List<Integer> getTaskTypeList() {
        return taskTypeList;
    }

    public void setTaskTypeList(List<Integer> taskTypeList) {
        this.taskTypeList = taskTypeList;
    }

    public List<Integer> getPeriodTypeList() {
        return periodTypeList;
    }

    public void setPeriodTypeList(List<Integer> periodTypeList) {
        this.periodTypeList = periodTypeList;
    }
}