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
@Table(name = "blog_like")
public class BlogLike  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_LIKE_ID")
	private Integer blogLikeId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID")
	private Customer customer;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="BLOG_ID",referencedColumnName = "BLOG_ID")
	private Blog blog;
	
	@Column(name = "BLOG_LIKE_STATUS",columnDefinition = "char")
	private String blogLikeStatus;
	
	public BlogLike() {
	}


	public Integer getBlogLikeId() {
		return blogLikeId;
	}


	public void setBlogLikeId(Integer blogLikeId) {
		this.blogLikeId = blogLikeId;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public String getBlogLikeStatus() {
		return blogLikeStatus;
	}

	public void setBlogLikeStatus(String blogLikeStatus) {
		this.blogLikeStatus = blogLikeStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}


	@Override
	public String toString() {
		return "BlogLike [blogLikeId=" + blogLikeId + ", customer=" + customer + ", createTime=" + createTime
				+ ", blog=" + blog + ", blogLikeStatus=" + blogLikeStatus + "]";
	}


}
