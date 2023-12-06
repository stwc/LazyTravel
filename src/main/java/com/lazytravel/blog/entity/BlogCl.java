package com.lazytravel.blog.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.lazytravel.customer.entity.Customer;

@Entity
@Table(name = "blog_d")
public class BlogCl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_CL_ID", updatable = false)
	private Integer blogClId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
	private Integer customerId;

	@Column(name = "LIKE_TIME")
	private Timestamp likeTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BLOG_ID", referencedColumnName = "BLOG_ID")
	private Blog blogId;

	@Column(name = "BLOG_CL_STATUS", columnDefinition = "char")
	private String blogClStatus;

	public BlogCl() {
	}

	public Integer getBlogClId() {
		return blogClId;
	}

	public void setBlogClId(Integer blogClId) {
		this.blogClId = blogClId;
	}


	public Timestamp getLikeTime() {
		return likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}

	public Blog getBlogId() {
		return blogId;
	}

	public void setBlogId(Blog blogId) {
		this.blogId = blogId;
	}

	public String getBlogClStatus() {
		return blogClStatus;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setBlogClStatus(String blogClStatus) {
		this.blogClStatus = blogClStatus;
	}

	@Override
	public String toString() {
		return "BlogCl [blogClId=" + blogClId + ", customerId=" + customerId + ", likeTime=" + likeTime + ", blogId="
				+ blogId + ", blogClStatus=" + blogClStatus + "]";
	}

}
