package com.lazytravel.journey.dao;

import java.util.ArrayList;
import java.util.List;

import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.JourneyDetail;
import com.lazytravel.util.JedisPoolUtil;

public class JourneyServiceImpl implements JourneyService{
	private JourneyDAO dao;
	private JourneyDetailDAO detailDao;
	private JourneySelectRedisDAO journeySelectRedisDao;

	public JourneyServiceImpl() {
		dao = new JourneyDAOImpl();
		detailDao = new JourneyDetailDAOImpl();
		journeySelectRedisDao = new JourneySelectRedisDAOImpl();
	}

	
	@Override
	public Integer addJourneyAndDetail(Journey journey, List<JourneyDetail> list) {
		Integer journeyId = dao.add(journey);
		detailDao.addList(list, journeyId);
		
		// 1.將List<JourneyDetail>中的foodScapeId存在一集合中
		List<Integer> foodScapeIdList = new ArrayList<>();
		
		for(JourneyDetail journeyDetail : list) {
			Integer foodScapeId = journeyDetail.getFoodScapeId();
			foodScapeIdList.add(foodScapeId);
		}
		
		// 2.根據新增的行程狀態，新增redis的Set
		String status = journey.getJourneyStatus();
		if(status.equals("1")) {
			journeySelectRedisDao.add(journeyId, foodScapeIdList);
		}
		
		JedisPoolUtil.getJedisPool().getResource().close();
		return journeyId;
	}
	
	@Override
	public void updateJourney(Journey journey) {
		dao.update(journey);
		
		// 1.將journeyId中包含的foodScapeId存在一集合中
		Integer journeyId = journey.getJourneyId();
		List<JourneyDetail> JourneyDetailList = detailDao.findByJourneyId(journeyId);
		List<Integer> foodScapeIdList = new ArrayList<>();
				
		for(JourneyDetail journeyDetail : JourneyDetailList) {
			Integer foodScapeId = journeyDetail.getFoodScapeId();
			foodScapeIdList.add(foodScapeId);
		}
		
		// 2.根據修改的行程狀態，新增或刪除redis的Set
		String status = journey.getJourneyStatus();
		if(status.equals("1")) {
			journeySelectRedisDao.add(journeyId, foodScapeIdList);
		} else {
			journeySelectRedisDao.delete(journeyId, foodScapeIdList);
		}
		
		JedisPoolUtil.getJedisPool().getResource().close();
	}

	@Override
	public Journey getOneJourney(Integer journeyId) {
		return dao.findByPK(journeyId);
	}

	@Override
	public List<Journey> getAll() {
		return dao.getAll();
	}
	
	@Override
	public List<Journey> getAllByStatusIsMarketed() {
		List<Journey> journeyList = dao.getAll();
		
		List<Journey> journeyIsMarketed = new ArrayList<>();
		for(Journey journey : journeyList) {
			String status = journey.getJourneyStatus();
			if(status.equals("1")) {
				journeyIsMarketed.add(journey);
			}
		}
		
		return journeyIsMarketed;
	}


	
	
// --------------------------------------------- JDBC -----------------------------------------------
//	public Journey addJourney(String journeyName, Integer price, String content, Integer days, String journeyStatus) {
//		
//		Journey journey = new Journey();
//		journey.setJourneyName(journeyName);
//		journey.setPrice(price);
//		journey.setContent(content);
//		journey.setDays(days);
//		journey.setJourneyStatus(journeyStatus);
//		dao.add(journey);
//		
//		return journey;
//	}
//	
//	public Journey updateJourney(Integer journeyId, String journeyName, Integer price, String content, Integer days,
//			String journeyStatus) {
//		
//		Journey journey = new Journey();
//		journey.setJourneyId(journeyId);
//		journey.setJourneyName(journeyName);
//		journey.setPrice(price);
//		journey.setContent(content);
//		journey.setDays(days);
//		journey.setJourneyStatus(journeyStatus);
//		dao.update(journey);
//		
//		return getOneJourney(journeyId);
//	}
//	
////    public void addJourney(Journey journey) {
////        dao.add(journey);
////    }
////	
////    public void updateJourney(Journey journey) {
////        dao.update(journey);
////    }
//	
////	public void deleteJourney(Integer journeyId) {
////		dao.delete(journeyId);	
////	}
//	
//	public Journey getOneJourney(Integer journeyId) {
//		return dao.findByPK(journeyId);
//	}
//	
//	public List<Journey> getAll(){
//		return dao.getAll();
//	}

}
