package com.lazytravel.journey.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.journey.dao.JourneyService;
import com.lazytravel.journey.entity.Journey;

@WebServlet(name = "JourneyServlet", value = "/journey/journey.do")
public class JourneyServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		
		if("getOne_For_Display".equals(action)) {   // 來自select_Journey_page.jsp的請求
			
			/***************************1.接收請求參數，並做錯誤處理 ****************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String str = req.getParameter("journey_id");
			if (str == null || str.trim().length() == 0) {
				errorMsgs.add("請輸入行程ID");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/journey/select_Journey_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer journeyId = null;
			try {
				journeyId = Integer.valueOf(req.getParameter("journey_id"));
			} catch (NumberFormatException e) {
				errorMsgs.add("行程ID: 格式不正確");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/journey/select_Journey_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.根據請求參數查詢資料，並做錯誤處理 **********************/
			JourneyService journeySvc = new JourneyService();
			Journey journey = journeySvc.getOneJourney(journeyId);
			if(journey == null) {
				errorMsgs.add("查無此行程ID");
				RequestDispatcher failureView = req.getRequestDispatcher("/journey/select_Journey_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*************************** 3.修改後, 轉交至 listOne_Journey.jsp ***************/
			req.setAttribute("journey", journey);
			RequestDispatcher successView = req.getRequestDispatcher("/journey/listOne_Journey.jsp");
			successView.forward(req, res);
		}
		
		
		
		if("getOne_For_Update".equals(action)) {    // 來自listAll_Journey.jsp的請求

			/***************************1.接收請求參數 **************************************/
			Integer journeyId = Integer.valueOf(req.getParameter("journey_id"));
			
			/***************************2.根據請求參數查詢資料 ********************************/
			JourneyService journeySvc = new JourneyService();
			Journey journey = journeySvc.getOneJourney(journeyId);
			
			/***************************3.查詢後, 轉交至 update_emp_input.jsp ***************/
			req.setAttribute("journey", journey);    // 資料庫取出的journey物件,存入req
			String url = "/journey/update_Journey_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);			
			successView.forward(req, res);           // 將 req 轉交至 update_Journey_input.jsp
		}
		
		
		
		if("update".equals(action)) {    // 來自update_Journey_input.jsp的請求
			
			/***************************1.接收請求參數，並做錯誤處理 ****************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer journeyId = Integer.valueOf(req.getParameter("journey_id").trim());	
					
			String journeyName = req.getParameter("journey_name").trim();
			String journeyNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (journeyName == null || journeyName.length() == 0) {
				errorMsgs.add("請輸入行程名稱");
			} else if (!journeyName.matches(journeyNameReg)) {
				errorMsgs.add("行程名稱: 格式不符，僅限輸入中、英文字母、數字和_,長度範圍為2到50字");
			}
			
			String content = req.getParameter("content").trim(); 
//			String contentReg = "^{5,}$";
			if (content == null || content.length() == 0) {
				errorMsgs.add("請輸入行程介紹");
//			} else if (!contentReg.matches(contentReg)) {
//				errorMsgs.add("行程介紹: 格式不符，僅限輸入中、英文字母、數字,長度至少5個字以上");
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

			String journeyStatus = String.valueOf(req.getParameter("journey_status").trim());
			
//			Timestamp createTime = null;
//			try {
//				createTime = Timestamp.valueOf(req.getParameter("create_time").trim());
//			} catch (IllegalArgumentException e) {
//				createTime = new Timestamp(System.currentTimeMillis());
//				errorMsgs.add("行程新增時間: 請輸入日期");
//			}

			Journey journey = new Journey();
			journey.setJourneyId(journeyId);
			journey.setJourneyName(journeyName);
			journey.setContent(content);
			journey.setPrice(price);
			journey.setDays(days);
			journey.setJourneyStatus(journeyStatus);
//			journey.setCreateTime(createTime);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("journey", journey);    // 將輸入格式錯誤的journey物件存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/journey/update_Journey_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.根據請求參數修改資料 *******************************/
			JourneyService journeySvc = new JourneyService();
			journey = journeySvc.updateJourney(journeyId, journeyName, price, content, days, journeyStatus);   // set兩次?????
			
			/*************************** 3.修改後, 轉交至 listOne_Journey.jsp ***************/
			req.setAttribute("journey", journey);     // update後的journey物件,存入req
			RequestDispatcher successView = req.getRequestDispatcher("/journey/listOne_Journey.jsp");
			successView.forward(req, res);
		}
		
		
		if("delete".equals(action)) {    // 來自listAll_Journey.jsp的請求  
			
			/***************************1.接收請求參數 **************************************/
			Integer journeyId = Integer.valueOf(req.getParameter("journey_id"));
			
			/***************************2.根據請求參數刪除資料 ********************************/
			JourneyService JourneySvc = new JourneyService();
			JourneySvc.deleteJourney(journeyId);
			
			/***************************3.查詢後, 轉交至 listAll_Journey.jsp ***************/
			RequestDispatcher successView = req.getRequestDispatcher("/journey/listAll_Journey.jsp");
			successView.forward(req, res);
		}
		
		
		if("insert".equals(action)) {    // 來自add_Journey.jsp的請求  

			/***************************1.接收請求參數，並做錯誤處理 ****************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
					
			String journeyName = req.getParameter("journey_name").trim();
			String journeyNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (journeyName == null || journeyName.length() == 0) {
				errorMsgs.add("請輸入行程名稱");
			} else if (!journeyName.matches(journeyNameReg)) {
				errorMsgs.add("行程名稱: 格式不符，僅限輸入中、英文字母、數字和_,長度範圍為2到50字");
			}
			
			String content = req.getParameter("content").trim(); 
//			String contentReg = "^{5,}$";
			if (content == null || content.length() == 0) {
				errorMsgs.add("請輸入行程介紹");
//			} else if (!contentReg.matches(contentReg)) {
//				errorMsgs.add("行程介紹: 格式不符，僅限輸入中、英文字母、數字,長度至少5個字以上");
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

			String journeyStatus = String.valueOf(req.getParameter("journey_status").trim());

			Journey journey = new Journey();
			journey.setJourneyName(journeyName);
			journey.setContent(content);
			journey.setPrice(price);
			journey.setDays(days);
			journey.setJourneyStatus(journeyStatus);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("journey", journey);    // 將輸入格式錯誤的journey物件存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/journey/add_Journey.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.根據請求參數修改資料 *******************************/
			JourneyService journeySvc = new JourneyService();
			journey = journeySvc.addJourney(journeyName, price, content, days, journeyStatus);   // set兩次?????
			
			/*************************** 3.修改後, 轉交至 listOne_Journey.jsp ***************/
			req.setAttribute("journey", journey);     // update後的journey物件,存入req
			RequestDispatcher successView = req.getRequestDispatcher("/journey/listAll_Journey.jsp");
			successView.forward(req, res);
		}
	}	
	
}
