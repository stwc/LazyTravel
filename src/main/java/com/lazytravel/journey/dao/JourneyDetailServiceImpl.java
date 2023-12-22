package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.JourneyDetail;

public class JourneyDetailServiceImpl implements JourneyDetailService {
	private JourneyDetailDAO dao;
	
	public JourneyDetailServiceImpl() {
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
	

}
