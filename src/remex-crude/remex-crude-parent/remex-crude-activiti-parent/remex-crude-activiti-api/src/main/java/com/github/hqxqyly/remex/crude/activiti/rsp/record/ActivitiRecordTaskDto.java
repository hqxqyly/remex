package com.github.hqxqyly.remex.crude.activiti.rsp.record;

import java.sql.Timestamp;

import com.github.hqxqyly.remex.crude.activiti.rsp.task.ActivitiTaskInfoDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("工作流任务流水dto")
public class ActivitiRecordTaskDto extends ActivitiTaskInfoDto {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("删除此任务的原因")
	protected String deleteReason;
	
	@ApiModelProperty("任务开始的时间")
	protected Timestamp startTime;
	
	@ApiModelProperty("删除或完成任务的时间")
	protected Timestamp endTime;
	
	@ApiModelProperty("endTime - startTime")
	protected Long durationInMillis;
	
	@ApiModelProperty("endTime - claimTime")
	protected Long workTimeInMillis;

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Long getDurationInMillis() {
		return durationInMillis;
	}

	public void setDurationInMillis(Long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}

	public Long getWorkTimeInMillis() {
		return workTimeInMillis;
	}

	public void setWorkTimeInMillis(Long workTimeInMillis) {
		this.workTimeInMillis = workTimeInMillis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
