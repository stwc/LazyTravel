package com.lazytravel.journey.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lazytravel.journey.dao.TourGroupDAO;
import com.lazytravel.journey.dao.TourGroupDAOImpl;
import com.lazytravel.journey.entity.TourGroup;

public class TourGroupServiceImpl implements TourGroupService {
	private TourGroupDAO dao;

	public TourGroupServiceImpl() {
		dao = new TourGroupDAOImpl();
	}

	@Override
	public void add(TourGroup tourGroup) {
		dao.add(tourGroup);
	}

	@Override
	public void update(TourGroup tourGroup) {
		dao.update(tourGroup);
	}
	
	@Override
	public void updateList(List<TourGroup> tourGroupList) {
		dao.updateList(tourGroupList);
	}
	
	@Override
	public TourGroup getOneTourGroup(Integer groupId) {
		return dao.findByPK(groupId);
	}

	@Override
	public List<TourGroup> getAll() {
		return dao.getAll();
	}

	@Override
	public List<TourGroup> getByTime(Map<String, String> map) {		
		Map<String, String> query = new HashMap<>();
		System.out.println("map = " + map.entrySet());
		
		Set<Map.Entry<String, String>> entry = map.entrySet();
		for(Map.Entry<String, String> row : entry) {
			// 排除action
			String key = row.getKey();
			if("action".equals(key)) {
				continue;
			}
			
			String value = row.getValue();
			if (value == null || value.isEmpty()) {
				continue;
			}
			
			query.put(key, value);
		}
        
		return dao.getByTime(query);
	}
	
	@Override
	public List<TourGroup> getByJourneyId(Integer journeyId){
		return dao.findByFK(journeyId);
	}
	
	@Override
	public List<TourGroup> getMarketedByJourneyId(Integer journeyId){
		List<TourGroup> tourGroupList = dao.findByFK(journeyId);
		
		List<TourGroup> tourGroupIsMarketed = new ArrayList<>();
		for(TourGroup tourGroup : tourGroupList) {
			String status = tourGroup.getGroupStatus();
			if(status.equals("1")) {
				tourGroupIsMarketed.add(tourGroup);
			}
		}
		
		return tourGroupIsMarketed;
	}

	

	
// --------------------------------------------- JDBC -----------------------------------------------	
//	public TourGroup addTourGroup(Integer journeyId, Date startTime, Date endTime, Integer price,
//			Integer signupNum, Integer minRequired, Integer maxRequired, Timestamp signupDate, Timestamp dueDate) {
//
//		TourGroup tourGroup = new TourGroup();
//		tourGroup.setJourneyId(journeyId);
//		tourGroup.setStartTime(startTime);
//		tourGroup.setEndTime(endTime);
//		tourGroup.setPrice(price);
//		tourGroup.setSignupNum(signupNum);
//		tourGroup.setMinRequired(minRequired);
//		tourGroup.setMaxRequired(maxRequired);
//		tourGroup.setSignupDate(signupDate);
//		tourGroup.setDueDate(dueDate);
//		dao.add(tourGroup);
//		
//		return tourGroup;
//	}
//	
//	public TourGroup updateTourGroup(Integer groupId, Integer journeyId, Date startTime, Date endTime,
//			Integer price, Integer signupNum, Integer minRequired, Integer maxRequired, Timestamp signupDate,
//			Timestamp dueDate, Timestamp createTime, Timestamp updateTime) {
//
//		TourGroup tourGroup = new TourGroup();
//		tourGroup.setGroupId(groupId);
//		tourGroup.setJourneyId(journeyId);
//		tourGroup.setStartTime(startTime);
//		tourGroup.setEndTime(endTime);
//		tourGroup.setPrice(price);
//		tourGroup.setSignupNum(signupNum);
//		tourGroup.setMinRequired(minRequired);
//		tourGroup.setMaxRequired(maxRequired);
//		tourGroup.setSignupDate(signupDate);
//		tourGroup.setDueDate(dueDate);
//		tourGroup.setCreateTime(createTime);
//		tourGroup.setUpdateTime(updateTime);
//		dao.update(tourGroup);
//
//		return tourGroup;
//	}
//	
////	public void addTourGroup(TourGroup tourGroup) {
////		dao.add(tourGroup);
////	}
////
////	public void updateTourGroup(TourGroup tourGroup) {
////		dao.update(tourGroup);
////	}
//	
//	public void daleteTourGroup(Integer groupId) {
//		dao.delete(groupId);
//	}
//	
//	public TourGroup getOneTourGroup(Integer groupId) {
//		return dao.findByPK(groupId);
//	}
//	
//	public List<TourGroup> getAll(){
//		return dao.getAll();
//	}

}
