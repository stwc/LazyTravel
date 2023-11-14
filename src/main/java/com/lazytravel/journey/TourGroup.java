package com.lazytravel.journey;

import java.io.Serializable;
import java.sql.Timestamp;

public class TourGroup implements Serializable {
	private Integer groupId;
	private Integer journeyId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer price;
	private Integer signupNum;
	private Integer minRequired;
	private Integer maxRequired;
	private Timestamp signupDate;
	private Timestamp dueDate;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public TourGroup() {
	}
			
	public TourGroup(Integer groupId, Integer journeyId, Timestamp startTime, Timestamp endTime, Integer price,
				     Integer signupNum, Integer minRequired, Integer maxRequired, Timestamp signupDate,
  			         Timestamp dueDate, Timestamp createTime, Timestamp updateTime) {
		this.groupId = groupId;
		this.journeyId = journeyId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
		this.signupNum = signupNum;
		this.minRequired = minRequired;
		this.maxRequired = maxRequired;
		this.signupDate = signupDate;
		this.dueDate = dueDate;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(Integer signupNum) {
		this.signupNum = signupNum;
	}

	public Integer getMinRequired() {
		return minRequired;
	}

	public void setMinRequired(Integer minRequired) {
		this.minRequired = minRequired;
	}

	public Integer getMaxRequired() {
		return maxRequired;
	}

	public void setMaxRequired(Integer maxRequired) {
		this.maxRequired = maxRequired;
	}

	public Timestamp getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Timestamp signupDate) {
		this.signupDate = signupDate;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
}
