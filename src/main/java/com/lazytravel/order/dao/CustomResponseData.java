package com.lazytravel.order.dao;

import java.sql.Timestamp;

public class CustomResponseData {
	 	
	private Integer couponId;
	private String couponName;
	    private String serialNo;
	    private Timestamp startTime;
	    private Timestamp endTime;
	    private Integer threshold;
	    private Integer discount;
	    private String couponStatus;
	    
	    
		public Integer getCouponId() {
			return couponId;
		}
		public void setCouponId(Integer couponId) {
			this.couponId = couponId;
		}
		public String getCouponName() {
			return couponName;
		}
		public void setCouponName(String couponName) {
			this.couponName = couponName;
		}
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
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
		public Integer getThreshold() {
			return threshold;
		}
		public void setThreshold(Integer threshold) {
			this.threshold = threshold;
		}
		public Integer getDiscount() {
			return discount;
		}
		public void setDiscount(Integer discount) {
			this.discount = discount;
		}
		public String getCouponStatus() {
			return couponStatus;
		}
		public void setCouponStatus(String couponStatus) {
			this.couponStatus = couponStatus;
		}
		@Override
		public String toString() {
			return "CustomResponseData [couponId=" + couponId + ", couponName=" + couponName + ", serialNo=" + serialNo
					+ ", startTime=" + startTime + ", endTime=" + endTime + ", threshold=" + threshold + ", discount="
					+ discount + ", couponStatus=" + couponStatus + "]";
		}
		
	    
	
		
	    
	    
}
