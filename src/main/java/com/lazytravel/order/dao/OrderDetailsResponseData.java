package com.lazytravel.order.dao;

import java.sql.Date;
import java.sql.Timestamp;

public class OrderDetailsResponseData {
	
	private String journeyName;
	private Integer price;
	private Date startTime;
	private Date endTime;
	
	
	
	
	public String getJourneyName() {
		return journeyName;
	}
	public void setJourneyName(String journeyName) {
		this.journeyName = journeyName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "OrderDetailsResponseData [journeyName=" + journeyName + ", price=" + price + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

	
	
}
