package com.lazytravel.journey.dao;

import java.sql.Timestamp;
import java.util.List;
import com.lazytravel.journey.entity.Journey;

public class JourneyService {
	private JourneyDAO dao;

	public JourneyService() {
		dao = new JourneyDAOImpl();
	}

	
	public Journey addJourney(String journeyName, Integer price, String content, Integer days, String journeyStatus) {
		
		Journey journey = new Journey();
		journey.setJourneyName(journeyName);
		journey.setPrice(price);
		journey.setContent(content);
		journey.setDays(days);
		journey.setJourneyStatus(journeyStatus);
		dao.add(journey);
		
		return journey;
	}
	
	public Journey updateJourney(Integer journeyId, String journeyName, Integer price, String content, Integer days,
			String journeyStatus) {
		
		Journey journey = new Journey();
		journey.setJourneyId(journeyId);
		journey.setJourneyName(journeyName);
		journey.setPrice(price);
		journey.setContent(content);
		journey.setDays(days);
		journey.setJourneyStatus(journeyStatus);
		dao.update(journey);
		
		return getOneJourney(journeyId);
	}
	
//    public void addJourney(Journey journey) {
//        dao.add(journey);
//    }
//	
//    public void updateJourney(Journey journey) {
//        dao.update(journey);
//    }
	
	public void deleteJourney(Integer journeyId) {
		dao.delete(journeyId);	
	}
	
	public Journey getOneJourney(Integer journeyId) {
		return dao.findByPK(journeyId);
	}
	
	public List<Journey> getAll(){
		return dao.getAll();
	}

}
