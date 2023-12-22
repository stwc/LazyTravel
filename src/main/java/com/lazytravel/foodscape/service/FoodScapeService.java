package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.entity.FoodScape;




public interface FoodScapeService {
//	private final FoodScapeDAO dao;
//
//	public FoodScapeService() {
//		dao = new FoodScapeDAOImpl();
//	}
//
//
//	public FoodScape getOneFoodScape(Integer foodscapeId) {
//		return dao.getByPK(foodscapeId);
//	}
//	
//    public List<FoodScape> getAll() {
//        return dao.getAll();
//    }
//
//	public FoodScape updateFoodScape(Integer foodscapeId, String foodscapeName, String city, String address, String phone,
//			String intro, String foodscapeStatus) {
//		FoodScape foodscape = new FoodScape();
//		foodscape.setFoodScapeId(foodscapeId);
//		foodscape.setFoodScapeName(foodscapeName);
//		foodscape.setIntro(intro);
//		foodscape.setCity(city);
//		foodscape.setAddress(address);
//		foodscape.setPhone(phone);
//		foodscape.setFoodScapeStatus(foodscapeStatus);
//		dao.update(foodscape);
//		return foodscape;
//	}
//
//	public FoodScape addFoodScape(String foodscapeName, String intro, String city, String address, String phone,
//			String foodscapeStatus, String category) {
//		FoodScape foodscape = new FoodScape();
//		foodscape.setFoodScapeName(foodscapeName);
//		foodscape.setIntro(intro);
//		foodscape.setCity(city);
//		foodscape.setAddress(address);
//		foodscape.setPhone(phone);
//		foodscape.setFoodScapeStatus(foodscapeStatus);
//		foodscape.setCategory(category);
//		dao.insert(foodscape);
//		return foodscape;
//	}

	void addFoodScape(FoodScape foodscape);
	
	void updateFoodScape(FoodScape foodscape);
	
//	void deleteFoodScape(Integer foodscapeId);
	
	FoodScape getFoodScapeByFoodScapeId(Integer foodScapeId);
	
	List<FoodScape> getAllFoodScapes(int currentPage);

	
	
}
