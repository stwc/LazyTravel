package com.lazytravel.foodscape;

import java.io.Serializable;

public class FoodScapeTag implements Serializable{
	private Integer tagId;
	private Integer foodScapeId;
	
	
	public FoodScapeTag(Integer tagId, Integer foodScapeId) {
		super();
		this.tagId = tagId;
		this.foodScapeId = foodScapeId;
	}


	public FoodScapeTag() {
		
	}


	public Integer getTagId() {
		return tagId;
	}


	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}


	public Integer getFoodScapeId() {
		return foodScapeId;
	}


	public void setFoodScapeId(Integer foodScapeId) {
		this.foodScapeId = foodScapeId;
	}
	
	
}

