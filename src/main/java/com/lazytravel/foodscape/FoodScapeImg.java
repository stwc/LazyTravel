package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;

public class FoodScapeImg implements Serializable{
	private Integer imgId;
	private Integer foodScapeId;
	private Date createTime;
	private byte[] blogImg;
	public FoodScapeImg(Integer imgId, Integer foodScapeId, Date createTime, byte[] blogImg) {
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

	public Integer getFoodScapeId() {
		return foodScapeId;
	}

	public void setFoodScapeId(Integer foodScapeId) {
		this.foodScapeId = foodScapeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public byte[] getBlogImg() {
		return blogImg;
	}

	public void setBlogImg(byte[] blogImg) {
		this.blogImg = blogImg;
	}
	
	

}

