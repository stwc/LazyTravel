package com.lazytravel.journey;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Journey implements Serializable {
	private Integer journeyId;
	private String name;
	private Integer price;
	private Timestamp createTime;
	private String content;
	private Double avgScore;
	private Integer scoreCount;
	private Date date;
	private Integer buyCount;
	private String status;

	public Journey() {
	}

	public Journey(Integer journeyId, String name, Integer price, Timestamp createTime, String content,
			       Double avgScore, Integer scoreCount, Date date, Integer buyCount, String status) {
		this.journeyId = journeyId;
		this.name = name;
		this.price = price;
		this.createTime = createTime;
		this.content = content;
		this.avgScore = avgScore;
		this.scoreCount = scoreCount;
		this.date = date;
		this.buyCount = buyCount;
		this.status = status;
	}

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
