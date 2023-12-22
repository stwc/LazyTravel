package com.lazytravel.foodscape.dao;

import java.util.List;

import com.lazytravel.foodscape.entity.FoodScapeImg;

public interface FoodScapeImgDAO {
	
	void add(FoodScapeImg foodscapeimg);
	
	void update(FoodScapeImg foodscapeimg);
	
	//int delete(Integer imgId, Integer foodScapeId);
	
	FoodScapeImg getByPK(Integer imgId, Integer foodScapeId);
	
	List<FoodScapeImg> getAll();
	
	List<FoodScapeImg> getAll(int currentPage);
	
}
