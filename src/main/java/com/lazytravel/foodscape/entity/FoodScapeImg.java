package com.lazytravel.foodscape.entity;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "foodscape_img")
public class FoodScapeImg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="IMG_ID",updatable = false)
	private Integer imgId;
	
	@ManyToOne
	@JoinColumn(name ="FOODSCAPE_ID",referencedColumnName = "FOODSCAPE_ID")
	private FoodScape foodScapeId;
	
	@Column(name ="CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name ="BLOG_IMG",columnDefinition = "longblob")
	private byte[] blogImg;
	
	public FoodScapeImg(Integer imgId, FoodScape foodScapeId, Timestamp createTime, byte[] blogImg) {
		super();
		this.imgId = imgId;
		this.foodScapeId = foodScapeId;
		this.createTime = createTime;
		this.blogImg = blogImg;
	}
	
	public FoodScapeImg() {
		
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public FoodScape getFoodScapeId() {
		return foodScapeId;
	}

	public void setFoodScapeId(FoodScape foodScapeId) {
		this.foodScapeId = foodScapeId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte[] getBlogImg() {
		return blogImg;
	}

	public void setBlogImg(byte[] blogImg) {
		this.blogImg = blogImg;
	}

	@Override
	public String toString() {
		return "FoodScapeImg [imgId=" + imgId + ", foodScapeId=" + foodScapeId + ", createTime=" + createTime
				+ ", blogImg=" + Arrays.toString(blogImg) + "]";
	}
	
	

}

