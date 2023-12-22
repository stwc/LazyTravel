package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.entity.FoodScapeImg;


public interface FoodScapeImgService {

	void addFoodScapeImg(FoodScapeImg foodscapeimg);
	
	void updateFoodScapeImg(FoodScapeImg foodscapeimg);
	
	//void deleteFoodScapeImg(Integer imgId);
	
	FoodScapeImg getFoodScapeImgByImgId(Integer imgId, Integer foodScapeId);
	
	List<FoodScapeImg> getAllFoodScapeImgs();
}
