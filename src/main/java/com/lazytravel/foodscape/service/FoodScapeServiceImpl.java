package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.customer.dao.CustomerDAOImpl;
import com.lazytravel.foodscape.dao.FoodScapeDAO;
import com.lazytravel.foodscape.dao.FoodScapeDAOImpl;
import com.lazytravel.foodscape.entity.FoodScape;

public class FoodScapeServiceImpl implements FoodScapeService{
	private final FoodScapeDAO dao;

    public FoodScapeServiceImpl() {
        dao = new FoodScapeDAOImpl();
    }

	public void addFoodScape(FoodScape foodscape) {
		dao.add(foodscape);

	}


	public void updateFoodScape(FoodScape foodscape) {
		dao.update(foodscape);
	}

	public FoodScape getFoodScapeByFoodScapeId(Integer foodScapeId) {
		return dao.getByPK(foodScapeId);
	}

	@Override
	public List<FoodScape> getAllFoodScapes() {

		return dao.getAll();
	}


}
