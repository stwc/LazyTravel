package com.lazytravel.foodscape;

public interface FoodScapeImgDAO {
	void add(FoodScapeImg foodScapeImg);
	void update(FoodScapeImg foodScapeImg);
	void delete(int imgId, int foodScapeId);
}
