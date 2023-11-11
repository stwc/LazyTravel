package com.lazytravel.blog;

import java.io.Serializable;
import java.sql.Timestamp;

public class BlogCl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer blogClId;
	private Integer customerId;
	private Timestamp likeTime;
	private Integer blogId;
	private String blogClStatus;
	
	public BlogCl() {
		super();
	}

	public BlogCl(Integer blogClId, Integer customerId, Timestamp likeTime, Integer blogId, String blogClStatus) {
		super();
		this.blogClId = blogClId;
		this.customerId = customerId;
		this.likeTime = likeTime;
		this.blogId = blogId;
		this.blogClStatus = blogClStatus;
	}

	public Integer getBlogClId() {
		return blogClId;
	}

	public void setBlogClId(Integer blogClId) {
		this.blogClId = blogClId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Timestamp getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getblogClStatus() {
		return blogClStatus;
	}

	public void setblogClStatus(String blogClStatus) {
		this.blogClStatus = blogClStatus;
	}
	public String toString() {
		return "BlogCl [blogClId=" + blogClId + ", customerId=" + customerId + ", likeTime=" + likeTime + ", blogId="
				+ blogId + ", blogClStatus=" + blogClStatus + "]";
	}
	
}