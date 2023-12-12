package com.lazytravel.foodscape.dao;

import java.util.List;
import java.util.Map;

import com.lazytravel.foodscape.entity.FoodScape;

public interface FoodScapeDAO {
	int insert(FoodScape foodscape);
	
	int update(FoodScape foodscape);
	
//	int delete(Integer foodScapeId);
	
	FoodScape getByPK(Integer foodScapeId);
	
	List<FoodScape> getAll();
	
	List<FoodScape> getAll(int currentPage);
	
//	List<FoodScape> getByCompositeQuery(Map<String, String> map);
	


}
