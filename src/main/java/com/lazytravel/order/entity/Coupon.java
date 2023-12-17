package com.lazytravel.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id", updatable = false)
	private Integer couponID;
	
	@Column(name = "serial_no" )
	private String serialNo;
	
	@Column(name = "coupon_name" )
	private String couponName;
	
	@Column(name = "discount" )
	private Integer discount;
	
	@Column(name = "threshold" )
	private Integer threshold;
	
	@Column(name = "start_time" )
	private Timestamp startTime;
	
	@Column(name = "end_time" )
	private Timestamp endTime;
	
	@Column(name = "stock" )
	private Integer stock;
	
	@Column(name = "total" )
	private Integer total;
	
	
	@Column(name = "coupon_status", columnDefinition = "char", nullable = false, length = 1)
	private String couponStatus = "1";

	
	public Coupon() {
		super();
	}

	public Coupon(Integer couponID, String serialNo, String couponName, Integer discount, Integer threshold,
			Timestamp startTime, Timestamp endTime, Integer stock, Integer total, String couponStatus) {
		super();
		this.couponID = couponID;
		this.serialNo = serialNo;
		this.couponName = couponName;
		this.discount = discount;
		this.threshold = threshold;
		this.startTime = startTime;
		this.endTime = endTime;
		this.stock = stock;
		this.total = total;
		this.couponStatus = couponStatus;
	}

	public Integer getCouponID() {
		return couponID;
	}

	public void setCouponID(Integer couponID) {
		this.couponID = couponID;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", serialNo=" + serialNo + ", couponName=" + couponName + ", discount="
				+ discount + ", threshold=" + threshold + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", stock=" + stock + ", total=" + total + ", couponStatus=" + couponStatus + "]";
	}


	
	
}
