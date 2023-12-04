package com.lazytravel.blog.entity;

import java.sql.Timestamp;
import java.util.Arrays;

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
@Table(name = "blog")
public class Blog {
	//UK的話要新增 unique=true
	@Id //自增主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="BLOG_ID",updatable = false)
	private Integer blogId;
	
	@Column(name="TITLE")
	private String title;
	
	@ManyToOne(fetch =FetchType.LAZY) 
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID")
	private Customer customerId;
	
	private Timestamp blogDate;
	
	@Column(name="CONTENT",columnDefinition = "LONGTEXT")
	private String content;
	
	@Column(name="UPDATE_TIME")
	private Timestamp upDateTime;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "LIKE_SUM")
	private Integer likeSum;
	
	@Column(name ="VIEW_SUM")
	private Integer viewSum;
	
	@Column(name ="CL_SUM")
	private Integer clSum;
	
	@Column(name = "IMG" ,columnDefinition = "longblob")
	private byte[]  img;
	
	@Column(name = "BLOG_STATUS",columnDefinition = "CHAR")
	private String blogStatus;

	public Blog() {
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public Timestamp getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Timestamp blogDate) {
		this.blogDate = blogDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getUpDateTime() {
		return upDateTime;
	}

	public void setUpDateTime(Timestamp upDateTime) {
		this.upDateTime = upDateTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getLikeSum() {
		return likeSum;
	}

	public void setLikeSum(Integer likeSum) {
		this.likeSum = likeSum;
	}

	public Integer getViewSum() {
		return viewSum;
	}

	public void setViewSum(Integer viewSum) {
		this.viewSum = viewSum;
	}

	public Integer getClSum() {
		return clSum;
	}

	public void setClSum(Integer clSum) {
		this.clSum = clSum;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getBlogStatus() {
		return blogStatus;
	}

	public void setBlogStatus(String blogStatus) {
		this.blogStatus = blogStatus;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", title=" + title + ", customerId=" + customerId + ", blogDate=" + blogDate
				+ ", content=" + content + ", upDateTime=" + upDateTime + ", createTime=" + createTime + ", likeSum="
				+ likeSum + ", viewSum=" + viewSum + ", clSum=" + clSum + ", img=" + Arrays.toString(img)
				+ ", blogStatus=" + blogStatus + "]";
	}

	
}
