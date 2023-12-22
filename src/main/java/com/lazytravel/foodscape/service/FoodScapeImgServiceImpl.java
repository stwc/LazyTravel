package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.dao.FoodScapeImgDAO;
import com.lazytravel.foodscape.dao.FoodScapeImgDAOImpl;
import com.lazytravel.foodscape.entity.FoodScapeImg;


public class FoodScapeImgServiceImpl implements FoodScapeImgService {
    private final FoodScapeImgDAO dao;

    public FoodScapeImgServiceImpl() {
        dao = new FoodScapeImgDAOImpl();
    }

    public void addFoodScapeImg(FoodScapeImg foodscapeimg) {
        dao.add(foodscapeimg);
    }

    public void updateFoodScapeImg(FoodScapeImg foodscapeimg) {
        dao.update(foodscapeimg);
    }

    public FoodScapeImg getFoodScapeImgByImgId(Integer imgId, Integer foodScapeId) {
        return dao.getByPK(imgId,foodScapeId);
    }

    public List<FoodScapeImg> getAllFoodScapeImgs() {
        return dao.getAll();
    }
}
