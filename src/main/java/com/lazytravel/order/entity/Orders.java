package com.lazytravel.order.entity;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "orders")
public class Orders {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", updatable = false)
	private Integer orderId;
	
	@Column(name = "customer_id" )
	private Integer customerId;
	
	@Column(name = "group_id" )
	private Integer groupId;
	
	@Column(name = "create_time", insertable = false, updatable = false)
	private Timestamp createTime;
	
	@Column(name = "paid_time" )
	private Timestamp paidTime;
	
	@Column(name = "total_amt" )
	private Integer totalAmt;
	
	@Column(name = "order_status", columnDefinition = "char" )
	private String orderStatus;
	
	@Column(name = "customer_point" )
	private Integer customerPoint;
	
	@Column(name = "coupon_id" )
	private Integer couponId;
	
	@Column(name = "tourist" )
	private Integer tourist;
	
	@Column(name = "content", columnDefinition = "longtext")
	private String content;
	
	@Column(name = "content_time" )
	private Timestamp contentTime;
	
	@Column(name = "score" )
	private Double score;
	
	@Column(name = "order_no" )
	private Integer orderNo;
	
	@Column(name = "update_time", insertable = false, updatable = false)
	private Timestamp updateTime;
	
	
	public Orders() {
		super();
	}


	public Orders(Integer orderId, Integer customerId, Integer groupId, Timestamp createTime, Timestamp paidTime,
			Integer totalAmt, String orderStatus, Integer customerPoint, Integer couponId, Integer tourist,
			String content, Timestamp contentTime, Double score, Integer orderNo, Timestamp updateTime) {
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
		this.updateTime = updateTime;
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
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


	public Timestamp getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customerId=" + customerId + ", groupId=" + groupId + ", createTime="
				+ createTime + ", paidTime=" + paidTime + ", totalAmt=" + totalAmt + ", orderStatus=" + orderStatus
				+ ", customerPoint=" + customerPoint + ", couponId=" + couponId + ", tourist=" + tourist + ", content="
				+ content + ", contentTime=" + contentTime + ", score=" + score + ", orderNo=" + orderNo
				+ ", updateTime=" + updateTime + "]";
	}


	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
