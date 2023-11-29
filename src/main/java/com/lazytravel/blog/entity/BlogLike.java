package com.lazytravel.blog;

import java.io.Serializable;
import java.sql.Timestamp;

public class BlogLike implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer bloglikeId;
	private Integer customerId;
	private Timestamp createTime;
	private Integer blogId;
	private String blogLikeStatus;
	
	public BlogLike() {
		super();
	}

	public BlogLike(Integer bloglikeId, Integer customerId, Timestamp createTime, Integer blogId,
			String blogLikeStatus) {
		super();
		this.bloglikeId = bloglikeId;
		this.customerId = customerId;
		this.createTime = createTime;
		this.blogId = blogId;
		this.blogLikeStatus = blogLikeStatus;
	}

	public Integer getBloglikeId() {
		return bloglikeId;
	}

	public void setBloglikeId(Integer bloglikeId) {
		this.bloglikeId = bloglikeId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getBlogLikeStatus() {
		return blogLikeStatus;
	}

	public void setBlogLikeStatus(String blogLikeStatus) {
		this.blogLikeStatus = blogLikeStatus;
	}
	public String toString() {
		return "BlogLike [bloglikeId=" + bloglikeId + ", customerId=" + customerId + ", createTime=" + createTime
				+ ", blogId=" + blogId + ", blogLikeStatus=" + blogLikeStatus + "]";
	}
	
}
