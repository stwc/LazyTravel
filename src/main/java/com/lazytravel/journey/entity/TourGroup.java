package com.lazytravel.journey.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tour_group")
public class TourGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id", updatable = false)
	private Integer groupId;

	// fetch 預設為 EAGER
	@ManyToOne
	@JoinColumn(name = "journey_id", referencedColumnName = "journey_id")
	private Journey journey;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "price")
	private Integer price;

	@Column(name = "signup_num")
	private Integer signupNum;

	@Column(name = "min_required")
	private Integer minRequired;

	@Column(name = "max_required")
	private Integer maxRequired;

	@Column(name = "signup_date")
	private Timestamp signupDate;

	@Column(name = "due_date")
	private Timestamp dueDate;

	@CreationTimestamp
	@Column(name = "create_time")
	private Timestamp createTime;

	@UpdateTimestamp
	@Column(name = "update_time")
	private Timestamp updateTime;

	public TourGroup() {
	}

	public TourGroup(Integer groupId, Journey journey, Date startTime, Date endTime, Integer price,
			Integer signupNum, Integer minRequired, Integer maxRequired, Timestamp signupDate, Timestamp dueDate,
			Timestamp createTime, Timestamp updateTime) {
		this.groupId = groupId;
		this.journey = journey;
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

	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
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

	@Override
	public String toString() {
		return "TourGroup [groupId=" + groupId + ", journey=" + journey+ ", startTime=" + startTime + ", endTime="
				+ endTime + ", price=" + price + ", signupNum=" + signupNum + ", minRequired=" + minRequired
				+ ", maxRequired=" + maxRequired + ", signupDate=" + signupDate + ", dueDate=" + dueDate
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	

}
