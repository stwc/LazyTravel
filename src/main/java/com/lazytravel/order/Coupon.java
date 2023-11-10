package com.lazytravel.order;

import java.io.Serializable;
import java.sql.Timestamp;


public class Coupon implements Serializable{
	
	private Integer couponID;
	private String serialNo;
	private String couponName;
	private Integer discount;
	private Integer threshold;
	private Timestamp startTime;
	private Timestamp endTime;
	private Integer stock;
	private Integer total;
	
	
	public Coupon() {
		super();
	}


	public Coupon(Integer couponID, String serialNo, String couponName, Integer discount, Integer threshold,
			Timestamp startTime, Timestamp endTime, Integer stock, Integer total) {
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


	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", serialNo=" + serialNo + ", couponName=" + couponName + ", discount="
				+ discount + ", threshold=" + threshold + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", stock=" + stock + ", total=" + total + "]";
	}

	
}
