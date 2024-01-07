package com.lazytravel.journey.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.journey.dao.JourneyService;
import com.lazytravel.journey.dao.JourneyServiceImpl;
import com.lazytravel.journey.dao.TourGroupService;
import com.lazytravel.journey.dao.TourGroupServiceImpl;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.TourGroup;

@WebServlet(name = "TourGroupServlet", value = "/journey/admin/tourGroup.do")
public class TourGroupServlet extends HttpServlet {
	
	private TourGroupService tourGroupSvc;
	private JourneyService journeySvc;
	
	
	@Override
	public void init() throws ServletException {
		tourGroupSvc = new TourGroupServiceImpl();
		journeySvc = new JourneyServiceImpl();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String forwardPath = "";
		String action = req.getParameter("action");
		
		switch (action) {
			case "tourGroup_search":
				forwardPath = getTourGroupListByTime(req, res);
				break;
			case "tourGroup_add":
				forwardPath = tourGroupAdd(req, res);
				break;
			case "tourGroup_modify":
				forwardPath = toTourGroupModifyPage(req, res);
				break;
			case "tourGroup_update":
				forwardPath = tourGroupUpdate(req, res);
				break;
			default:
				forwardPath = "/journey/admin/tourGroup_list.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	
	private String getTourGroupListByTime(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String[]> paramMap = req.getParameterMap();
		Map<String, String> map = new HashMap<>();
		
	    for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
	        String key = entry.getKey();
	        String values = entry.getValue()[0];
	        map.put(key, values);
	    }
	    
		List<TourGroup> tourGroupList = tourGroupSvc.getByTime(map);
		req.setAttribute("tourGroupList", tourGroupList);
		
		// 判斷條件
		req.setAttribute("searchPerformed", true);
		
		System.out.println("map = " + map.entrySet());
		return "/journey/admin/tourGroup_list.jsp";
	}
	
	
	private String tourGroupAdd(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 1.接收請求參數 和 錯誤處理
		Integer journeyId = Integer.valueOf(req.getParameter("journey_id").trim());
		String journeyName = req.getParameter("journey_name").trim();
		
		Date startTime = null;
		try {
			startTime = Date.valueOf(req.getParameter("start_time").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.add("請輸入旅行團開始日");
		}
		
		Date endTime = null;
		try {
			endTime = Date.valueOf(req.getParameter("end_time").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.add("請輸入旅行團結束日");
		}
		
		Integer price = null;
		try {
			price = Integer.valueOf(req.getParameter("price").trim());
			if(price < 1) {
				errorMsgs.add("價格: 請輸入大於零的數字");
			}
		} catch (NumberFormatException e) {
			price = 0;
			errorMsgs.add("價格: 請輸入大於零的數字");
		}
		
		Integer minRequired = null;
		try {
			minRequired = Integer.valueOf(req.getParameter("min_required").trim());
			if(minRequired < 1) {
				errorMsgs.add("成團人數: 請輸入大於零的數字");
			}
		} catch (NumberFormatException e) {
			minRequired = 0;
			errorMsgs.add("成團人數: 請輸入大於零的數字");
		}
		
		Integer maxRequired = null;
		try {
			maxRequired = Integer.valueOf(req.getParameter("max_required").trim());
			if(maxRequired < minRequired) {
				errorMsgs.add("報名人數上限: 需大於或等於成團人數");
			}
		} catch (NumberFormatException e) {
			maxRequired = 0;
			errorMsgs.add("報名人數上限: 請輸入大於零的數字");
		}
		
		Timestamp signupDate = null;
		try {
			String signupDateStr = req.getParameter("signup_date").trim();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	        java.util.Date parsedSignupDate = dateFormat.parse(signupDateStr);
	        signupDate = new Timestamp(parsedSignupDate.getTime());
		} catch (ParseException e) {
		    errorMsgs.add("請輸入報名開始日");
		}
		
		Timestamp dueDate = null;
		try {
			String dueDateStr = req.getParameter("due_date").trim();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	        java.util.Date parsedDueDate = dateFormat.parse(dueDateStr);
	        dueDate = new Timestamp(parsedDueDate.getTime());
		} catch (ParseException e) {
		    errorMsgs.add("請輸入報名結束日");
		}
		
		Integer signupNum = Integer.valueOf(req.getParameter("sign_num"));
	
		// 根據報名時間判斷旅行團狀態
		String groupStatus = null;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		if(signupDate !=null && dueDate != null) {
		    Integer compareSignStart = currentTime.compareTo(signupDate);
		    Integer compareSignEnd = currentTime.compareTo(dueDate);
		    if (compareSignStart < 0 || compareSignEnd > 0) {      // 當前時間小於報名開始時間，或大於報名結束時間
		    	groupStatus = "0";
		    } else {
		    	groupStatus = "1";
		    }
		}
		
		TourGroup tourGroup = new TourGroup();
		tourGroup.setStartTime(startTime);
		tourGroup.setEndTime(endTime);
		tourGroup.setPrice(price);
		tourGroup.setMinRequired(minRequired);
		tourGroup.setMaxRequired(maxRequired);
		tourGroup.setSignupDate(signupDate);
		tourGroup.setDueDate(dueDate);
		tourGroup.setSignupNum(signupNum);
		tourGroup.setGroupStatus(groupStatus);
		
		Journey journey1 = new Journey();
		journey1.setJourneyId(journeyId);
		journey1.setJourneyName(journeyName);
		tourGroup.setJourney(journey1);		
		
		
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("tourGroup", tourGroup);
			
			req.setAttribute("journeyId", journeyId);
			req.setAttribute("journeyName", journeyName);
			
			return "/journey/admin/tourGroup_on.jsp";
		}
		
		// 2.根據請求參數修改資料
		tourGroupSvc.add(tourGroup);
		
		// 3.update後的物件,存入req
		req.setAttribute("tourGroup", tourGroup);

		req.setAttribute("journey", journey1);
		req.setAttribute("journeyId", journeyId);
		req.setAttribute("journeyName", journeyName);
		
		
		return "/journey/admin/tourGroup_list.jsp";
	}

	
	private String toTourGroupModifyPage(HttpServletRequest req, HttpServletResponse res) {
		// 1.接收請求參數
		Integer groupId = Integer.valueOf(req.getParameter("group_id").trim());
		
		// 2.根據請求參數查詢資料
		TourGroup tourGroup = tourGroupSvc.getOneTourGroup(groupId);
		List<Journey> journey = journeySvc.getAll();
		
		// 3.資料庫取出的tourGroup物件,存入req
		req.setAttribute("tourGroup", tourGroup);
		req.setAttribute("journey", journey);
		
		return "/journey/admin/tourGroup_update.jsp";
	}
	
	
	private String tourGroupUpdate(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 1.接收請求參數 和 錯誤處理
		Integer groupId = Integer.valueOf(req.getParameter("group_id").trim());
		
		Integer journeyId = Integer.valueOf(req.getParameter("journey_id").trim());
		String journeyName = req.getParameter("journey_name").trim();
		
		Date startTime = null;
		try {
			startTime = Date.valueOf(req.getParameter("start_time").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.add("請輸入旅行團開始日");
		}
		
		Date endTime = null;
		try {
			endTime = Date.valueOf(req.getParameter("end_time").trim());
		} catch (IllegalArgumentException e) {
			errorMsgs.add("請輸入旅行團結束日");
		}
		
		Integer price = null;
		try {
			price = Integer.valueOf(req.getParameter("price").trim());
			if(price < 1) {
				errorMsgs.add("價格: 請輸入大於零的數字");
			}
		} catch (NumberFormatException e) {
			price = 0;
			errorMsgs.add("價格: 請輸入大於零的數字");
		}
		
		Integer minRequired = null;
		try {
			minRequired = Integer.valueOf(req.getParameter("min_required").trim());
			if(minRequired < 1) {
				errorMsgs.add("成團人數: 請輸入大於零的數字");
			}
		} catch (NumberFormatException e) {
			minRequired = 0;
			errorMsgs.add("成團人數: 請輸入大於零的數字");
		}
		
		Integer maxRequired = null;
		try {
			maxRequired = Integer.valueOf(req.getParameter("max_required").trim());
			if(maxRequired < minRequired) {
				errorMsgs.add("報名人數上限: 需大於或等於成團人數");
			}
		} catch (NumberFormatException e) {
			maxRequired = 0;
			errorMsgs.add("報名人數上限: 請輸入大於零的數字");
		}
		
		Timestamp signupDate = null;
		try {
			String signupDateStr = req.getParameter("signup_date").trim();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	        java.util.Date parsedSignupDate = dateFormat.parse(signupDateStr);
	        signupDate = new Timestamp(parsedSignupDate.getTime());
		} catch (ParseException e) {
		    errorMsgs.add("請輸入報名開始日");
		}
		
		Timestamp dueDate = null;
		try {
			String dueDateStr = req.getParameter("due_date").trim();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	        java.util.Date parsedDueDate = dateFormat.parse(dueDateStr);
	        dueDate = new Timestamp(parsedDueDate.getTime());
		} catch (ParseException e) {
		    errorMsgs.add("請輸入報名開始日");
		}
		
		// 根據報名時間判斷旅行團狀態
		String groupStatus = null;
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	    Integer compareSignStart = currentTime.compareTo(signupDate);
	    Integer compareSignEnd = currentTime.compareTo(dueDate);
	    if (compareSignStart < 0 || compareSignEnd > 0) {      // 當前時間小於報名開始時間，或大於報名結束時間
	    	groupStatus = "0";
	    } else {
	    	groupStatus = "1";
	    }
	    
		
		// 下面欄位不能透過後台修改資料	
		Integer signupNum = Integer.valueOf(req.getParameter("sign_num"));
		
		Timestamp createTime = null;
	    try {
	    	String createTimeStr = req.getParameter("create_time").trim();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        java.util.Date parsedCreateTime = dateFormat.parse(createTimeStr);
	        createTime = new Timestamp(parsedCreateTime.getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
		Timestamp updateTime = null;
	    try {
	    	String updateTimeStr = req.getParameter("update_time").trim();
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	java.util.Date parsedUpdateTime = dateFormat.parse(updateTimeStr);
	    	updateTime = new Timestamp(parsedUpdateTime.getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		TourGroup tourGroup = new TourGroup();
		tourGroup.setGroupId(groupId);
		tourGroup.setStartTime(startTime);
		tourGroup.setEndTime(endTime);
		tourGroup.setPrice(price);
		tourGroup.setMinRequired(minRequired);
		tourGroup.setMaxRequired(maxRequired);
		tourGroup.setSignupDate(signupDate);
		tourGroup.setDueDate(dueDate);
		tourGroup.setSignupNum(signupNum);
		tourGroup.setCreateTime(createTime);
		tourGroup.setUpdateTime(updateTime);
		tourGroup.setGroupStatus(groupStatus);
		
		Journey journey1 = journeySvc.getOneJourney(journeyId);
		journey1.setJourneyId(journeyId);
		journey1.setJourneyName(journeyName);
		tourGroup.setJourney(journey1);		
		
		
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("tourGroup", tourGroup);
			
			req.setAttribute("journeyId", journeyId);
			req.setAttribute("journeyName", journeyName);
			
			return "/journey/admin/tourGroup_update.jsp";
		}
		
		// 2.根據請求參數修改資料
		tourGroupSvc.update(tourGroup);
		
		// 3.update後的物件,存入req
		req.setAttribute("tourGroup", tourGroup);

		req.setAttribute("journey", journey1);
		req.setAttribute("journeyId", journeyId);
		req.setAttribute("journeyName", journeyName);
		
		return "/journey/admin/tourGroup_list.jsp";
	}

}
