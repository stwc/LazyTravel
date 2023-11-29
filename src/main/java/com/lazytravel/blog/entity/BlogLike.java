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

import com.lazytravel.example.entity.Customer;

@Entity
@Table(name = "blog_like")
public class BlogLike  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_LIKE_ID")
	private Integer bloglikeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID")
	private Customer customer;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="BLOG_ID",referencedColumnName = "BLOG_ID")
	private Blog blogId;
	
	@Column(name = "BLOG_LIKE_STATUS",columnDefinition = "char")
	private String blogLikeStatus;
	
	public BlogLike() {
	}

	public Integer getBloglikeId() {
		return bloglikeId;
	}

	public void setBloglikeId(Integer bloglikeId) {
		this.bloglikeId = bloglikeId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Blog getBlogId() {
		return blogId;
	}

	public void setBlogId(Blog blogId) {
		this.blogId = blogId;
	}

	public String getBlogLikeStatus() {
		return blogLikeStatus;
	}

	public void setBlogLikeStatus(String blogLikeStatus) {
		this.blogLikeStatus = blogLikeStatus;
	}

	@Override
	public String toString() {
		return "BlogLike [bloglikeId=" + bloglikeId + ", customer=" + customer + ", createTime=" + createTime
				+ ", blogId=" + blogId + ", blogLikeStatus=" + blogLikeStatus + "]";
	}

}
