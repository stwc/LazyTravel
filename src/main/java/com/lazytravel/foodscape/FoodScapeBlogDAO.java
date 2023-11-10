package com.lazytravel.foodscape;

public interface FoodScapeBlogDAO {
	void add(FoodScapeBlog foodScapeBlog);
	void update(FoodScapeBlog foodScapeBlog);
	void delete(int blogId, int foodScapeId);
}
