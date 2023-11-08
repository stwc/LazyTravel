package com.lazytravel.journey;

import java.io.Serializable;
import java.sql.Timestamp;

public class JourneyTimeFoodscape implements Serializable {
	private Integer journeyId;
	private Integer foodscapeId;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer day;
	private Timestamp updateTime;

	public JourneyTimeFoodscape() {

	}

	public JourneyTimeFoodscape(Integer journeyId, Integer foodscapeId, Timestamp startTime,
		                        Timestamp endTime, Integer day, Timestamp updateTime) {
		this.journeyId = journeyId;
		this.foodscapeId = foodscapeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.day = day;
		this.updateTime = updateTime;
	}

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}

	public Integer getFoodscapeId() {
		return foodscapeId;
	}

	public void setFoodscapeId(Integer foodscapeId) {
		this.foodscapeId = foodscapeId;
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

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
