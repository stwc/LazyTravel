package com.lazytravel.journey.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	public List<TourGroup> getAll() {
		return dao.getAll();
	}

	@Override
	public List<TourGroup> getByTime(Map<String, String> map) {
		Map<String, String> query = new HashMap<>();
		Set<Map.Entry<String, String>> entry = map.entrySet();
		
		for(Map.Entry<String, String> row : entry) {
			// 排除action
			String key = row.getKey();
			if("action".equals(key)) {
				continue;
			}
			
			// 過濾沒有查詢的欄位值
			String value = row.getValue();
			if(value == null || value.isEmpty()) {
				continue;
			}
			
			query.put(key, value);
		}
		return dao.getByTime(query);
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
