package com.lazytravel.journey.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.journey.service.JourneyDetailService;
import com.lazytravel.journey.service.JourneyDetailServiceImpl;

@WebServlet(name = "LocationServlet", value = "/journey/user/mapLocation.do")
public class LocationServlet extends HttpServlet {

	private JourneyDetailService journeyDetailSvc;
	
	@Override
	public void init() throws ServletException {
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
			case "map_location":
				forwardPath = getMapLocation(req, res);
				break;
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	
	private String getMapLocation(HttpServletRequest req, HttpServletResponse res) {
		String loop = req.getParameter("mapButton");
		String nthDayName = "nthDay_" + loop;

		Integer nthDay = Integer.valueOf(req.getParameter(nthDayName));
		Integer journeyId = Integer.valueOf(req.getParameter("journeyId"));
		System.out.println("-------------------------- nthDay: " + nthDayName);
		System.out.println("-------------------------- journeyId: " + journeyId);
		
		List<FoodScape> foodScapeMapList = journeyDetailSvc.findFoodscapeLngAndLat(journeyId, nthDay);
		System.out.println("-------------------------- foodScapeMapList: " + foodScapeMapList);
		
		// 轉成 JSON
		Gson gson = new Gson();
		String locationsJsonStr = gson.toJson(foodScapeMapList);
		System.out.println("List to JSON: " + locationsJsonStr);
		
		req.setAttribute("locationsJsonStr", locationsJsonStr);
	
		try {
			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(locationsJsonStr);
		} catch (IOException e) {
			System.out.println("編碼錯誤");
			e.printStackTrace();
		}
		
		return "/journey/user/journeySelect_More.jsp";
	}
	

}