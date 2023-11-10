package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;

public class OpenTimeList implements Serializable{
	private Integer openTimeId;
	private Integer foodScapeId;
	private Date startTime;
	private Date endTime;
	private char week;



public OpenTimeList() {
	
}



public OpenTimeList(Integer openTimeId, Integer foodScapeId, Date startTime, Date endTime, char week) {
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



public Date getStartTime() {
	return startTime;
}



public void setStartTime(Date startTime) {
	this.startTime = startTime;
}



public Date getEndTime() {
	return endTime;
}



public void setEndTime(Date endTime) {
	this.endTime = endTime;
}



public char getWeek() {
	return week;
}



public void setWeek(char week) {
	this.week = week;
}


}