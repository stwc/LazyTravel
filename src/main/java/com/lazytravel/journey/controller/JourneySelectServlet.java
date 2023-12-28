package com.lazytravel.journey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.service.FoodScapeService;
import com.lazytravel.foodscape.service.FoodScapeServiceImpl;
import com.lazytravel.journey.dao.JourneySelectRedisService;
import com.lazytravel.journey.dao.JourneySelectRedisServiceImpl;
import com.lazytravel.journey.dao.TourGroupService;
import com.lazytravel.journey.dao.TourGroupServiceImpl;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.TourGroup;

@WebServlet(name = "JourneySelectServlet", value = "/journey/user/journeySelect.do")
public class JourneySelectServlet extends HttpServlet{

	private TourGroupService tourGroupSvc;
	private JourneySelectRedisService journeySelectRedisSvc;
	private FoodScapeService foodScapeSvc;
	
	@Override
	public void init() throws ServletException {
		tourGroupSvc = new TourGroupServiceImpl();
		journeySelectRedisSvc = new JourneySelectRedisServiceImpl();
		foodScapeSvc = new FoodScapeServiceImpl();
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
			case "receiveFoodScapeId":
				forwardPath = receiveFoodScapeIdFromPreviousPage(req, res);
				break;
			case "journeySelect_more":
				forwardPath = toJourneyMorePage(req, res);
				break;
			case "includeFragment":
				forwardPath = includeFragment(req, res);
				break;
			case "journeySelect_order":
				forwardPath = toOrderPage(req, res);
				break;
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}


	private String toJourneyMorePage(HttpServletRequest req, HttpServletResponse res) {
		String index = req.getParameter("loopIndex");
		String journeyIdName = "journeyId_" + index;
		Integer journeyId = Integer.valueOf(req.getParameter(journeyIdName));
		
		// 送資料
		HttpSession session = req.getSession();
		session.setAttribute("journeyId", journeyId); 
		
		return "/journey/user/journeySelect_More.jsp";
	}

	
	private String toOrderPage(HttpServletRequest req, HttpServletResponse res) {	
		String groupId  = req.getParameter("groupId");
		Integer signupNum = Integer.valueOf(req.getParameter("signupNum"));
		
		// 送資料
		HttpSession session = req.getSession();
		session.setAttribute("groupId", groupId); 
		session.setAttribute("signupNum", signupNum); 
		
		return "/order/checkOut.jsp";
	}
	
	
	private String includeFragment(HttpServletRequest req, HttpServletResponse res) {
		String groupIdStr  = req.getParameter("groupId");
		Integer groupId = null;
		
		if(groupIdStr != null) {
			groupId = Integer.valueOf(groupIdStr);
			
			TourGroup tourGroup = tourGroupSvc.getOneTourGroup(groupId);
			req.setAttribute("tourGroup", tourGroup);
			
			req.setAttribute("searchTourGroupData", true);   // 旗標
			return "/journey/user/journeySelect_More.jsp";   // 查詢完成後轉交jsp，由其 include file 處理後續

		} else {
			return "/journey/user/journeySelect_More.jsp";
		}
	}
	
	
	private String receiveFoodScapeIdFromPreviousPage(HttpServletRequest req, HttpServletResponse res) {
		// 接收上一個頁面送過來的景點資料
		String foodScapeIdListStr = (String) req.getSession().getAttribute("foodScapeIdList_Str");
		
		if(foodScapeIdListStr == null) {
		    return "/journey/user/journeySelect.jsp";
		}
		
		// 將景點資料轉為一 List
		List<Integer> foodScapeIdList = new ArrayList<>();
		String[] foodScapeIdStr = foodScapeIdListStr.split(",");
		
		for (String str : foodScapeIdStr) {
			Integer foodScapeId = Integer.parseInt(str.trim());
			foodScapeIdList.add(foodScapeId);
		}
		System.out.println(foodScapeIdList);
		
		
		List<Map.Entry<Journey, Integer>> list = journeySelectRedisSvc.getEntryList(foodScapeIdList);
		req.setAttribute("list", list);
		

		// 用於顯示已勾選的美食/景點 和 匹配率
		Integer selectedCount = 0;
		List<String> foodScapeNameList = new ArrayList<>();
		for (Integer foodScapeId : foodScapeIdList) {
			FoodScape foodScape = foodScapeSvc.getFoodScapeByFoodScapeId(foodScapeId);
			String foodScapeName = foodScape.getFoodScapeName();
			foodScapeNameList.add(foodScapeName);
			selectedCount++;
		}
		req.setAttribute("foodScapeNameList", foodScapeNameList);
		req.setAttribute("selectedCount", selectedCount);
		
		return "/journey/user/journeySelect.jsp";
	}
		
}
