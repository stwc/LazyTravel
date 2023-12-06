package com.lazytravel.journey.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "journey")
public class Journey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "journey_id")
	private Integer journeyId;
	
	@Column(name = "journey_name")
	private String journeyName;
	
	@Column(name = "price")
	private Integer price;
	
	@CreationTimestamp
	@Column(name = "create_time")
	private Timestamp createTime;
	
	@Column(name = "content", columnDefinition = "longtext")
	private String content;
	
	@Column(name = "avg_score", insertable = false)
	private Double avgScore;
	
	@Column(name = "score_count", insertable = false)
	private Integer scoreCount;
	
	@Column(name = "days")
	private Integer days;
	
	@Column(name = "buy_count", insertable = false)
	private Integer buyCount;
	
	@Column(name = "journey_status", columnDefinition = "char")
	private String journeyStatus;
	
	// fetch 預設為 LAZY
	@OneToMany(mappedBy = "journey", cascade = CascadeType.ALL)
	@OrderBy("groupId asc")
	private Set<TourGroup> tourGroup;
	
	public Journey() {
	}

	public Journey(Integer journeyId, String journeyName, Integer price, Timestamp createTime, String content,
			       Double avgScore, Integer scoreCount, Integer days, Integer buyCount, String journeyStatus) {
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
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

	public Set<TourGroup> getTourGroup() {
		return tourGroup;
	}

	public void setTourGroup(Set<TourGroup> tourGroup) {
		this.tourGroup = tourGroup;
	}

	@Override
	public String toString() {
		return "Journey [journeyId=" + journeyId + ", journeyName=" + journeyName + ", price=" + price + ", createTime="
				+ createTime + ", content=" + content + ", avgScore=" + avgScore + ", scoreCount=" + scoreCount
				+ ", days=" + days + ", buyCount=" + buyCount + ", journeyStatus=" + journeyStatus + "]";
	}
	
	
}
