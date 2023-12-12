package com.lazytravel.foodscape.dao;

import java.util.List;

import com.lazytravel.foodscape.entity.FoodScapeImg;


public interface FoodScapeImgService {

	FoodScapeImg insertFoodScapeImg(FoodScapeImg foodscapeimg);
	
	FoodScapeImg updateFoodScapeImg(FoodScapeImg foodscapeimg);
	
	void deleteFoodScapeImg(Integer imgId);
	
	FoodScapeImg getFoodScapeImgByImgId(Integer imgId);
	
	List<FoodScapeImg> getAllFoodScapeImgs(int currentPage);
}
