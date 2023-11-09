package com.lazytravel.journey;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Journey implements Serializable {
	private Integer journeyId;
	private String journeyName;
	private Integer price;
	private Timestamp createTime;
	private String content;
	private Double avgScore;
	private Integer scoreCount;
	private Date days;
	private Integer buyCount;
	private String journeyStatus;

	public Journey() {
	}

	public Journey(Integer journeyId, String journeyName, Integer price, Timestamp createTime, String content,
			       Double avgScore, Integer scoreCount, Date days, Integer buyCount, String journeyStatus) {
		this.journeyId = journeyId;
		this.journeyName = journeyName;
		this.price = price;
		this.createTime = createTime;
		this.content = content;
		this.avgScore = avgScore;
		this.scoreCount = scoreCount;
		this.days = days;
		this.buyCount = buyCount;
		this.journeyStatus = journeyStatus;
	}

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}

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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public Integer getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}

	public Date getDays() {
		return days;
	}

	public void setDays(Date days) {
		this.days = days;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public String getJourneyStatus() {
		return journeyStatus;
	}

	public void setJourneyStatus(String journeyStatus) {
		this.journeyStatus = journeyStatus;
	}
	
}
