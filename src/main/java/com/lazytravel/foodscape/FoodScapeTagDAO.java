package com.lazytravel.foodscape;

public interface FoodScapeTagDAO {
	void add(FoodScapeTag foodScapeTag);
	void update(FoodScapeTag foodScapeTag);
	void delete(int tagId, int foodScapeId);

}
