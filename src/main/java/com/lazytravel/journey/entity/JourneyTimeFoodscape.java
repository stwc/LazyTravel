package com.lazytravel.journey.entity;

import java.sql.Time;
import java.sql.Timestamp;

public class JourneyTimeFoodscape {
	private Integer journeyId;
	private Integer foodscapeId;
	private Time startTime;
	private Time endTime;
	private Integer nthDay;
	private Timestamp updateTime;

	public JourneyTimeFoodscape() {

	}

	public JourneyTimeFoodscape(Integer journeyId, Integer foodscapeId, Time startTime, Time endTime, Integer nthDay,
			Timestamp updateTime) {
		this.journeyId = journeyId;
		this.foodscapeId = foodscapeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.nthDay = nthDay;
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Integer getNthDay() {
		return nthDay;
	}

	public void setNthDay(Integer nthDay) {
		this.nthDay = nthDay;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "JourneyTimeFoodscape [journeyId=" + journeyId + ", foodscapeId=" + foodscapeId + ", startTime="
				+ startTime + ", endTime=" + endTime + ", nthDay=" + nthDay + ", updateTime=" + updateTime + "]";
	}

}
