package com.lazytravel.order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;



public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer customerId;
	private Integer groupId;
	private Timestamp createTime;
	private Timestamp paidTime;
	private Integer totalAmt;
	private String orderStatus;
	private Integer customerPoint;
	private Integer couponId;
	private Integer tourist;
	private byte[] content;
	private Timestamp contentTime;
	private Double score;
	private Integer orderNo;
	
	
	public Orders() {
		super();
	}


	public Orders(Integer orderId, Integer customerId, Integer groupId, Timestamp createTime, Timestamp paidTime,
			Integer totalAmt, String orderStatus, Integer customerPoint, Integer couponId, Integer tourist,
			byte[] content, Timestamp contentTime, Double score, Integer orderNo) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.groupId = groupId;
		this.createTime = createTime;
		this.paidTime = paidTime;
		this.totalAmt = totalAmt;
		this.orderStatus = orderStatus;
		this.customerPoint = customerPoint;
		this.couponId = couponId;
		this.tourist = tourist;
		this.content = content;
		this.contentTime = contentTime;
		this.score = score;
		this.orderNo = orderNo;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Timestamp getPaidTime() {
		return paidTime;
	}


	public void setPaidTime(Timestamp paidTime) {
		this.paidTime = paidTime;
	}


	public Integer getTotalAmt() {
		return totalAmt;
	}


	public void setTotalAmt(Integer totalAmt) {
		this.totalAmt = totalAmt;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Integer getCustomerPoint() {
		return customerPoint;
	}


	public void setCustomerPoint(Integer customerPoint) {
		this.customerPoint = customerPoint;
	}


	public Integer getCouponId() {
		return couponId;
	}


	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}


	public Integer getTourist() {
		return tourist;
	}


	public void setTourist(Integer tourist) {
		this.tourist = tourist;
	}


	public byte[] getContent() {
		return content;
	}


	public void setContent(byte[] content) {
		this.content = content;
	}


	public Timestamp getContentTime() {
		return contentTime;
	}


	public void setContentTime(Timestamp contentTime) {
		this.contentTime = contentTime;
	}


	public Double getScore() {
		return score;
	}


	public void setScore(Double score) {
		this.score = score;
	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customerId=" + customerId + ", groupId=" + groupId + ", createTime="
				+ createTime + ", paidTime=" + paidTime + ", totalAmt=" + totalAmt + ", orderStatus=" + orderStatus
				+ ", customerPoint=" + customerPoint + ", couponId=" + couponId + ", tourist=" + tourist + ", content="
				+ Arrays.toString(content) + ", contentTime=" + contentTime + ", score=" + score + ", orderNo="
				+ orderNo + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
