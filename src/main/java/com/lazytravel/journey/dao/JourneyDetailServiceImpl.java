package com.lazytravel.journey.dao;

import java.util.ArrayList;
import java.util.List;

import com.lazytravel.foodscape.dao.FoodScapeDAO;
import com.lazytravel.foodscape.dao.FoodScapeDAOImpl;
import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.journey.entity.JourneyDetail;

public class JourneyDetailServiceImpl implements JourneyDetailService {
	private JourneyDetailDAO dao;
	private FoodScapeDAO foodScapeDAO;
	
	public JourneyDetailServiceImpl() {
		dao = new JourneyDetailDAOImpl();
		foodScapeDAO = new FoodScapeDAOImpl();
	}
	
	
	@Override
	public void add(JourneyDetail journeyDetail) {
		dao.add(journeyDetail);
	}

	@Override
	public void update(JourneyDetail journeyDetail) {
		dao.update(journeyDetail);
	}

	@Override
	public void delete(Integer journeyId, Integer foodscapeId) {
		dao.delete(journeyId, foodscapeId);
	}

//	@Override
//	public JourneyDetail getOneJourneyDetail(Integer journeyId, Integer foodscapeId) {
//		return dao.findByPk(journeyId, foodscapeId);
//	}
	
	@Override
	public List<JourneyDetail> getByJourneyId(Integer journeyId) {
		return dao.findByJourneyId(journeyId);
	}

	@Override
	public List<JourneyDetail> addList(List<JourneyDetail> journeyDetail, Integer journeyId) {
		return dao.addList(journeyDetail, journeyId);
	}
	
	@Override
	public List<FoodScape> findFoodscapeNameAndAddress(Integer journeyId) {
		List<JourneyDetail> journeyDetailList = dao.findByJourneyId(journeyId);
		
		List<FoodScape> foodScapeList = new ArrayList<>();
		
		for(JourneyDetail journeyDetail : journeyDetailList) {
				Integer foodScapeId = journeyDetail.getFoodScapeId();
				Integer nthDay = journeyDetail.getNthDay();
				
				FoodScape foodScape = foodScapeDAO.getByPK(foodScapeId);
				String foodScapeName = foodScape.getFoodScapeName();
				String address = foodScape.getAddress();
				
				foodScapeList.add(new FoodScape(nthDay, foodScapeName, address));
		}
		return foodScapeList;
	}
	

}
