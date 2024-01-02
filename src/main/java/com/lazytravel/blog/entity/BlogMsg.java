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
@Table(name="blog_msg")
public class BlogMsg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BLOG_MSG_ID")
	private Integer blogMsgId;
	
	@Column(name= "CONTENT",columnDefinition = "longtext")
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="BLOG_ID",referencedColumnName = "BLOG_ID")
	private Blog blog;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID")
	private Customer customer;
	
	@Column(name ="CREATE_TIME")
	private Timestamp createTime;

	public BlogMsg() {
	}

	public Integer getBlogMsgId() {
		return blogMsgId;
	}

	public void setBlogMsgId(Integer blogMsgId) {
		this.blogMsgId = blogMsgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
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

	@Override
	public String toString() {
		return "BlogMsg [blogMsgId=" + blogMsgId + ", content=" + content + ", blog=" + blog + ", customer=" + customer
				+ ", createTime=" + createTime + "]";
	}

}