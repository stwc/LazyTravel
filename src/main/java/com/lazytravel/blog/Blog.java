package com.lazytravel.blog;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer blogId;
	private String title;
	private Integer customerId;
	private Timestamp blogDate;
	private Integer content;
	private Timestamp upDateTime;
	private Timestamp createTime;
	private Integer likeSum;
	private Integer viewSum;
	private Integer clSum;
	private byte[]  img;
	private String blogStatus;

	public Blog() {
		super();
	}

	public Blog(Integer blogId, String title, Integer customerId, Timestamp blogDate, Integer content,
			Timestamp upDateTime, Timestamp createTime, Integer likeSum, Integer viewSum, Integer clSum, byte[] img,
			String blogStatus) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.customerId = customerId;
		this.blogDate = blogDate;
		this.content = content;
		this.upDateTime = upDateTime;
		this.createTime = createTime;
		this.likeSum = likeSum;
		this.viewSum = viewSum;
		this.clSum = clSum;
		this.img = img;
		this.blogStatus = blogStatus;
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Timestamp getBlogDate() {
		return blogDate;
	}

	public void setBlogDate(Timestamp blogDate) {
		this.blogDate = blogDate;
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
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
	public String toString() {
		return "Blog [blogId=" + blogId + ", title=" + title + ", customerId=" + customerId + ", blogDate=" + blogDate
				+ ", content=" + content + ", upDateTime=" + upDateTime + ", createTime=" + createTime + ", likeSum="
				+ likeSum + ", viewSum=" + viewSum + ", clSum=" + clSum + ", img=" + Arrays.toString(img)
				+ ", blogStatus=" + blogStatus + "]";
	}


}
