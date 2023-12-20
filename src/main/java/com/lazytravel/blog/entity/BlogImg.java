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

@Entity
@Table(name = "blog_img")
public class BlogImg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="BLOG_IMG_ID")
	private Integer blogImgId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="BLOG_ID",referencedColumnName = "BLOG_ID")
	private Blog blog;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "IMG" ,columnDefinition = "longblob")
	private byte[] img;
	
	public BlogImg() {

	}

	public Integer getBlogImgId() {
		return blogImgId;
	}

	public void setBlogImgId(Integer blogImgId) {
		this.blogImgId = blogImgId;
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


	@Override
	public String toString() {
		return "BlogImg [blogImgId=" + blogImgId + ", blog=" + blog + ", createTime=" + createTime + ", img="
				+ Arrays.toString(img) + "]";
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

}
