package com.lazytravel.journey.dao;

import java.util.List;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.JourneyDetail;

public class JourneyServiceImpl implements JourneyService{
	private JourneyDAO dao;
	private JourneyDetailDAO detailDao;

	public JourneyServiceImpl() {
		dao = new JourneyDAOImpl();
		detailDao = new JourneyDetailDAOImpl();
	}

	
	@Override
	public Integer addJourneyAndDetail(Journey journey, List<JourneyDetail> list) {
		Integer journeyId = dao.add(journey);
		detailDao.addList(list);
		return journeyId;
	}
	
	@Override
	public void updateJourney(Journey journey) {
		dao.update(journey);
	}

	@Override
	public Journey getOneJourney(Integer journeyId) {
		return dao.findByPK(journeyId);
	}

	@Override
	public List<Journey> getAll() {
		return dao.getAll();
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
