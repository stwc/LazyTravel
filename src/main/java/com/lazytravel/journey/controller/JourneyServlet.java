package com.lazytravel.journey.controller;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.journey.dao.JourneyDetailService;
import com.lazytravel.journey.dao.JourneyDetailServiceImpl;
import com.lazytravel.journey.dao.JourneyService;
import com.lazytravel.journey.dao.JourneyServiceImpl;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.JourneyDetail;

@WebServlet(name = "JourneyServlet", value = "/journey/admin/journey.do")
public class JourneyServlet extends HttpServlet {
	
	private JourneyService journeySvc;
	private JourneyDetailService journeyDetailSvc;
	
	@Override
	public void init() throws ServletException {
		journeySvc = new JourneyServiceImpl();
		journeyDetailSvc = new JourneyDetailServiceImpl();
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
			case "journey_detail_search":
				forwardPath = getJourneyDetail(req, res);
				break;
			case "journeyAndDetail_add":
				forwardPath = addJourneyAndDetail(req, res);
				break;
			case "journey_modify":
				forwardPath = toJourneyModifyPage(req, res);
				break;
			case "journey_update":
				forwardPath = journeyUpdate(req, res);
				break;
			default:
				forwardPath = "/journey/admin/journey_list.jsp";
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	
	private String getJourneyDetail(HttpServletRequest req, HttpServletResponse res) {
		// 1.接收請求參數
		Integer journeyId = Integer.valueOf(req.getParameter("journey_id").trim());
		
		// 2.根據請求參數查詢資料
		Journey journey = journeySvc.getOneJourney(journeyId);
		List<JourneyDetail> journeyDetailList = journeyDetailSvc.getByJourneyId(journeyId);
		
		// 3.資料庫取出的journey物件,存入req
		req.setAttribute("journey", journey);    
		req.setAttribute("journeyDetailList", journeyDetailList);    

		return "/journey/admin/journeyDetail_list.jsp";
	}

	
	private String addJourneyAndDetail(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		
//		// 1.接收請求參數 和 錯誤處理				
//		String journeyName = req.getParameter("journey_name");
//		String journeyNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
//		if (journeyName == null || journeyName.trim().length() == 0) {
//			errorMsgs.add("請輸入行程名稱");
//		} else if (!journeyName.matches(journeyNameReg)) {
//			errorMsgs.add("行程名稱: 格式不符，僅限輸入中、英文字母、數字和_,長度範圍為2到50字");
//		}
//		
//		Integer price = null;
//		try {
//			price = Integer.valueOf(req.getParameter("price").trim());
//			if (price < 1) {
//				errorMsgs.add("價格: 請輸入大於零的數字");
//			}
//		} catch (NumberFormatException e) {
//			price = 0;
//			errorMsgs.add("價格: 請輸入大於零的數字");
//		}
//		
//		Integer days = null;
//		try {
//			days = Integer.valueOf(req.getParameter("days").trim());
//			if (days < 1) {
//				errorMsgs.add("行程天數: 請輸入大於零的數字");
//			}
//		} catch (NumberFormatException e) {
//			days = 0;
//			errorMsgs.add("行程天數: 請輸入大於零的數字");
//		}
//
//		String journeyStatus = String.valueOf(req.getParameter("journey_status"));
//		
//		String content = req.getParameter("content"); 
//		String contentReg = "^.{5,}$";
//		if (content == null || content.trim().length() == 0) {
//			errorMsgs.add("請輸入行程介紹");
//		} else if (!content.matches(contentReg)) {
//			errorMsgs.add("行程介紹: 長度最少5個字");
//		}
//		
//		Journey journey = new Journey();
//		journey.setJourneyName(journeyName);
//		journey.setPrice(price);
//		journey.setDays(days);
//		journey.setJourneyStatus(journeyStatus);
//		journey.setContent(content);
//		
//		
////		List<JourneyDetail> journeyDetailList = new ArrayList<>();
////		
////		int journeyDetailListSize = (int) req.getAttribute("journeyDetailListSize");
////		for (int i = 1; i < journeyDetailListSize+1; i++) {
////			JourneyDetail detail = new JourneyDetail();
//			
//			Integer nthDay = null;
//			try {
//				nthDay = Integer.valueOf(req.getParameter("nth_day").trim());
//				if(nthDay < 1) {
////					errorMsgs.add("第" + i + "筆 行程細項-第幾天 : 請輸入大於零的數字");
//				}
//			} catch (NumberFormatException e) {
//				nthDay = 0;
////				errorMsgs.add("第" + i + "筆 行程細項-第幾天 : 請輸入大於零的數字");
//			}
//			
//			Integer foodScapeId = Integer.valueOf(req.getParameter("foodscape_id"));
//			String foodScapeIdStr = req.getParameter("foodscape_id");
//			if (foodScapeIdStr == null || foodScapeIdStr.trim().length() == 0) {
////				errorMsgs.add("第" + i + "筆 行程細項-美食景點ID : 欄位不可以為空");
//			}
//			
//			String startTimeStr = req.getParameter("start_time");
//			Time startTime = null;
//			if (startTimeStr != null && !startTimeStr.trim().isEmpty()) {
//			    try {
//			        LocalTime localTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
//			        startTime = Time.valueOf(localTime);
//			    } catch (DateTimeParseException e) {
////					errorMsgs.add("第" + i + "筆 行程細項-時間起訖 : 開始時間格式有誤");
//			    }
//			} else {
////				errorMsgs.add("第" + i + "筆 行程細項-時間起訖 : 開始時間不可以為空");
//			}
//			
//			String endTimeStr = req.getParameter("end_time");
//			Time endTime = null;
//			if (startTimeStr != null && !startTimeStr.trim().isEmpty()) {
//			    try {
//			        LocalTime localTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
//			        startTime = Time.valueOf(localTime);
//			    } catch (DateTimeParseException e) {
////					errorMsgs.add("第" + i + "筆 行程細項-時間起訖 : 結束時間格式有誤");
//			    }
//			} else {
////				errorMsgs.add("第" + i + "筆 行程細項-時間起訖 : 結束時間不可以為空");
//			}
//
////			
////			journeyDetailList.add(detail);
////		}
//			
//			List<JourneyDetail> journeyDetailList = new ArrayList<>();
//			
//			JourneyDetail journeyDetail = new JourneyDetail();
//			journeyDetail.setFoodScapeId(foodScapeId);
//			journeyDetail.setNthDay(nthDay);
//			journeyDetail.setStartTime(startTime);
//			journeyDetail.setEndTime(endTime);
//
//			journeyDetailList.add(journeyDetail);
//
//
//		
//		
//		if(!errorMsgs.isEmpty()) {
//			req.setAttribute("journey", journey);    
//			req.setAttribute("journeyDetailList", journeyDetailList);    
//			return "/journey/admin/journeyAndDetail_on.jsp";
//		}
//		
//		// 2.根據請求參數新增資料
//		Integer journeyId = journeySvc.addJourneyAndDetail(journey, journeyDetailList);
//		
//		// 3.update後的journey物件,存入req
//		req.setAttribute("journey", journeySvc.getOneJourney(journeyId));
//		req.setAttribute("journeyDetailList", journeyDetailSvc.getByJourneyId(journeyId));
		return "/journey/admin/journey_list.jsp";		
	}
	
	
	private String toJourneyModifyPage(HttpServletRequest req, HttpServletResponse res) {
		// 1.接收請求參數
		Integer journeyId = Integer.valueOf(req.getParameter("journey_id").trim());
		
		// 2.根據請求參數查詢資料
		Journey journey = journeySvc.getOneJourney(journeyId);
		List<JourneyDetail>  journeyDetailList = journeyDetailSvc.getByJourneyId(journeyId);
		
		// 3.資料庫取出的journey物件,存入req
		req.setAttribute("journey", journey);    
		req.setAttribute("journeyDetailList", journeyDetailList);    

		return "/journey/admin/journey_update.jsp";
	}
	
	
	private String journeyUpdate(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 1.接收請求參數 和 錯誤處理
		Integer journeyId = Integer.valueOf(req.getParameter("journey_id"));	
				
		String journeyName = req.getParameter("journey_name");
		String journeyNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
		if (journeyName == null || journeyName.trim().length() == 0) {
			errorMsgs.add("請輸入行程名稱");
		} else if (!journeyName.matches(journeyNameReg)) {
			errorMsgs.add("行程名稱: 格式不符，僅限輸入中、英文字母、數字和_,長度範圍為2到50字");
		}
		
		Integer price = null;
		try {
			price = Integer.valueOf(req.getParameter("price").trim());
			if (price < 1) {
				errorMsgs.add("價格: 請輸入大於零的數字");
			}
		} catch (NumberFormatException e) {
			price = 0;
			errorMsgs.add("價格: 請輸入大於零的數字");
		}
		
		Integer days = null;
		try {
			days = Integer.valueOf(req.getParameter("days").trim());
			if (days < 1) {
				errorMsgs.add("行程天數: 請輸入大於零的數字");
			}
		} catch (NumberFormatException e) {
			days = 0;
			errorMsgs.add("行程天數: 請輸入大於零的數字");
		}

		String journeyStatus = String.valueOf(req.getParameter("journey_status"));
		
		String content = req.getParameter("content"); 
		String contentReg = "^.{5,}$";
		if (content == null || content.trim().length() == 0) {
			errorMsgs.add("請輸入行程介紹");
		} else if (!content.matches(contentReg)) {
			errorMsgs.add("行程介紹: 長度最少5個字");
		}
		
		// 下面四個欄位不能透過後台修改資料	
		Timestamp createTime = null;
	    try {
	    	String createTimeStr = req.getParameter("create_time").trim();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date parsedCreateTime = dateFormat.parse(createTimeStr);
	        createTime = new Timestamp(parsedCreateTime.getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		Double avgScore = Double.valueOf(req.getParameter("avg_score").trim());
		Integer scoreCount = Integer.valueOf(req.getParameter("score_count").trim());
		Integer buyCount = Integer.valueOf(req.getParameter("buy_count").trim());
		

		Journey journey = new Journey();
		journey.setJourneyId(journeyId);
		journey.setJourneyName(journeyName);
		journey.setPrice(price);
		journey.setDays(days);
		journey.setJourneyStatus(journeyStatus);
		journey.setContent(content);
		journey.setCreateTime(createTime);
		journey.setAvgScore(avgScore);
		journey.setScoreCount(scoreCount);
		journey.setBuyCount(buyCount);
		
		
		if(!errorMsgs.isEmpty()) {
			req.setAttribute("journey", journey);    // 將輸入格式錯誤的journey物件存入req
			return "/journey/admin/journey_update.jsp";
		}

		// 2.根據請求參數修改資料
		journeySvc.updateJourney(journey);
		
		// 3.update後的journey物件,存入req
		req.setAttribute("journey", journeySvc.getOneJourney(journeyId));
		
		return "/journey/admin/journey_list.jsp";
	}
	
}
