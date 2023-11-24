package com.lazytravel.journey.dao;

import java.sql.Timestamp;
import java.util.List;

import com.lazytravel.journey.entity.TourGroup;

public class TourGroupService {
	private TourGroupDAO dao;

	public TourGroupService() {
		dao = new TourGroupDAOImpl();
	}

	
	public TourGroup addTourGroup(Integer journeyId, Timestamp startTime, Timestamp endTime, Integer price,
			Integer signupNum, Integer minRequired, Integer maxRequired, Timestamp signupDate, Timestamp dueDate) {

		TourGroup tourGroup = new TourGroup();
		tourGroup.setJourneyId(journeyId);
		tourGroup.setStartTime(startTime);
		tourGroup.setEndTime(endTime);
		tourGroup.setPrice(price);
		tourGroup.setSignupNum(signupNum);
		tourGroup.setMinRequired(minRequired);
		tourGroup.setMaxRequired(maxRequired);
		tourGroup.setSignupDate(signupDate);
		tourGroup.setDueDate(dueDate);
		dao.add(tourGroup);
		
		return tourGroup;
	}
	
	public TourGroup updateTourGroup(Integer groupId, Integer journeyId, Timestamp startTime, Timestamp endTime,
			Integer price, Integer signupNum, Integer minRequired, Integer maxRequired, Timestamp signupDate,
			Timestamp dueDate, Timestamp createTime, Timestamp updateTime) {

		TourGroup tourGroup = new TourGroup();
		tourGroup.setGroupId(groupId);
		tourGroup.setJourneyId(journeyId);
		tourGroup.setStartTime(startTime);
		tourGroup.setEndTime(endTime);
		tourGroup.setPrice(price);
		tourGroup.setSignupNum(signupNum);
		tourGroup.setMinRequired(minRequired);
		tourGroup.setMaxRequired(maxRequired);
		tourGroup.setSignupDate(signupDate);
		tourGroup.setDueDate(dueDate);
		tourGroup.setCreateTime(createTime);
		tourGroup.setUpdateTime(updateTime);
		dao.update(tourGroup);

		return tourGroup;
	}
	
//	public void addTourGroup(TourGroup tourGroup) {
//		dao.add(tourGroup);
//	}
//
//	public void updateTourGroup(TourGroup tourGroup) {
//		dao.update(tourGroup);
//	}
	
	public void daleteTourGroup(Integer groupId) {
		dao.delete(groupId);
	}
	
	public TourGroup getOneTourGroup(Integer groupId) {
		return dao.findByPK(groupId);
	}
	
	public List<TourGroup> getAll(){
		return dao.getAll();
	}

}
