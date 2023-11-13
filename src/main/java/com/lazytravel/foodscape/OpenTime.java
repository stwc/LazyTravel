package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class OpenTime implements Serializable{
	private Integer openTimeId;
	private Integer foodScapeId;
	private Time startTime;
	private Time endTime;
	private String week;



public OpenTime() {
	
}



public OpenTime(Integer openTimeId, Integer foodScapeId, Time startTime, Time endTime, String week) {
	super();
	this.openTimeId = openTimeId;
	this.foodScapeId = foodScapeId;
	this.startTime = startTime;
	this.endTime = endTime;
	this.week = week;
}



public Integer getOpenTimeId() {
	return openTimeId;
}



public void setOpenTimeId(Integer openTimeId) {
	this.openTimeId = openTimeId;
}



public Integer getFoodScapeId() {
	return foodScapeId;
}



public void setFoodScapeId(Integer foodScapeId) {
	this.foodScapeId = foodScapeId;
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



public String getWeek() {
	return week;
}



public void setWeek(String week) {
	this.week = week;
}


}