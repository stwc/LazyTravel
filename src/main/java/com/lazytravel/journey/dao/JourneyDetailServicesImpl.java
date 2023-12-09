package com.lazytravel.journey.dao;

import com.lazytravel.journey.entity.JourneyDetail;

public class JourneyDetailServicesImpl implements JourneyDetailServices {
	private JourneyDetailDAO dao;
	
	public JourneyDetailServicesImpl() {
		dao = new JourneyDetailDAOImpl();
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

	@Override
	public JourneyDetail getOneJourneyDetail(Integer journeyId, Integer foodscapeId) {
		return dao.findByPk(journeyId, foodscapeId);
	}

}
