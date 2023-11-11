package com.lazytravel.order;

import java.sql.Timestamp;

public class CustomerCoupon {
	
	private Integer customerId;
	private Integer couponId;
	private String couponStatus;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	
	public CustomerCoupon() {
		super();
	}
	

	public CustomerCoupon(Integer customerId, Integer couponId, String couponStatus, Timestamp createTime,
			Timestamp updateTime) {
		super();
		this.customerId = customerId;
		this.couponId = couponId;
		this.couponStatus = couponStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}


	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public Integer getCouponId() {
		return couponId;
	}


	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}


	public String getCouponStatus() {
		return couponStatus;
	}


	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
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
		return "CustomerCoupon [customerId=" + customerId + ", couponId=" + couponId + ", couponStatus=" + couponStatus
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
	

}
