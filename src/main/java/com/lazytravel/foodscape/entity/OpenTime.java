package com.lazytravel.foodscape.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "opentime")
public class OpenTime {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="OPENTIME_ID",updatable = false)
	private Integer openTimeId;
	
	@ManyToOne
	@JoinColumn(name ="FOODSCAPE_ID",referencedColumnName = "FOODSCAPE_ID")
	private FoodScape foodScapeId;
	
	@Column(name="START_TIME")
	private Time startTime;
	
	@Column(name="END_TIME")
	private Time endTime;
	
	@Column(name="OPENTIME_WEEK",columnDefinition = "char")
	private String week;



public OpenTime() {
	
}



public OpenTime(Integer openTimeId, FoodScape foodScapeId, Time startTime, Time endTime, String week) {
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



public FoodScape getFoodScapeId() {
	return foodScapeId;
}



public void setFoodScapeId(FoodScape foodScapeId) {
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



@Override
public String toString() {
	return "OpenTime [openTimeId=" + openTimeId + ", foodScapeId=" + foodScapeId + ", startTime=" + startTime
			+ ", endTime=" + endTime + ", week=" + week + "]";
}



public void setStartTime(String startTimeStr) {
	// TODO Auto-generated method stub
	
}



public void setEndTime(String endTimeStr) {
	// TODO Auto-generated method stub
	
}


}