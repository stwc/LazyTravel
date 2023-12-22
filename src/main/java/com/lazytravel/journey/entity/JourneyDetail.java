package com.lazytravel.journey.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lazytravel.journey.entity.JourneyDetail.CompositeJourneyAndFoodscape;

@Entity
@Table(name = "journey_detail")
@IdClass(CompositeJourneyAndFoodscape.class)
public class JourneyDetail {

	@Id
	@Column(name = "journey_id")
	private Integer journeyId;
	
	@Id
	@Column(name = "foodscape_id")
	private Integer foodScapeId;
	
	@Column(name = "start_time")
	private Time startTime;
	
	@Column(name = "end_time")
	private Time endTime;
	
	@Column(name = "nth_day")
	private Integer nthDay;
	
    @Transient
    private Integer index;    // 用於jsp和Servlet中，判斷新增的物件為第幾筆行程明細

	public JourneyDetail() {

	}

	public JourneyDetail(Integer journeyId, Integer foodScapeId, Time startTime, Time endTime, Integer nthDay, Integer index) {
		this.journeyId = journeyId;
		this.foodScapeId = foodScapeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.nthDay = nthDay;
		this.index = index;
	}

	public JourneyDetail(Integer foodScapeId, Time startTime, Time endTime, Integer nthDay, Integer index) {
		super();
		this.foodScapeId = foodScapeId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.nthDay = nthDay;
		this.index = index;
	}

	public Integer getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(Integer journeyId) {
		this.journeyId = journeyId;
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

	public Integer getNthDay() {
		return nthDay;
	}

	public void setNthDay(Integer nthDay) {
		this.nthDay = nthDay;
	}
	
	public CompositeJourneyAndFoodscape getCompositeKey() {
		return new CompositeJourneyAndFoodscape(journeyId, foodScapeId);
	}

	public void setCompositeKey(CompositeJourneyAndFoodscape key) {
		this.journeyId = key.getJourneyId();
		this.foodScapeId = key.getFoodScapeId();
	}
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	// 宣告包含複合主鍵屬性的內部類別
	public static class CompositeJourneyAndFoodscape implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer journeyId;
		private Integer foodScapeId;
		
		public CompositeJourneyAndFoodscape() {
			super();
		}
		
		public CompositeJourneyAndFoodscape(Integer journeyId, Integer foodScapeId) {
			super();
			this.journeyId = journeyId;
			this.foodScapeId = foodScapeId;
		}

		public Integer getJourneyId() {
			return journeyId;
		}

		public void setJourneyId(Integer journeyId) {
			this.journeyId = journeyId;
		}

		public Integer getFoodScapeId() {
			return foodScapeId;
		}

		public void setFoodScapeId(Integer foodScapeId) {
			this.foodScapeId = foodScapeId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(foodScapeId, journeyId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompositeJourneyAndFoodscape other = (CompositeJourneyAndFoodscape) obj;
			return Objects.equals(foodScapeId, other.foodScapeId) && Objects.equals(journeyId, other.journeyId);
		}
	}

}
