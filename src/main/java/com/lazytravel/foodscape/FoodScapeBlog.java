package com.lazytravel.foodscape;

import java.io.Serializable;

public class FoodScapeBlog implements Serializable{
	private Integer foodScapeId;
	private Integer blogId;
	
public FoodScapeBlog() {
	
}

public Integer getFoodScapeId() {
	return foodScapeId;
}

public void setFoodScapeId(Integer foodScapeId) {
	this.foodScapeId = foodScapeId;
}

public Integer getBlogId() {
	return blogId;
}

public void setBlogId(Integer blogId) {
	this.blogId = blogId;
}


}
