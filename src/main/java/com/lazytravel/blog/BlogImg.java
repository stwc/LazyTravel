package com.lazytravel.blog;

import java.io.Serializable;
import java.sql.Timestamp;

public class BlogImg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer blogImgId;
	private Integer blogId;
	private Timestamp createTime;
	private byte[] img;
	
	public BlogImg() {
		super();
	}

	public BlogImg(Integer blogImgId, Integer blogId, Timestamp createTime, byte[] img) {
		super();
		this.blogImgId = blogImgId;
		this.blogId = blogId;
		this.createTime = createTime;
		this.img = img;
	}

	public Integer getBlogImgId() {
		return blogImgId;
	}

	public void setBlogImgId(Integer blogImgId) {
		this.blogImgId = blogImgId;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
	public String toString() {
		return "BlogImg [blogImgId=" + blogImgId + ", blogId=" + blogId + ", createTime=" + createTime + ", img=" + img
				+ "]";
	}
	
}
