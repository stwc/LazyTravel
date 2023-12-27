package com.lazytravel.journey.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lazytravel.journey.dao.TourGroupService;
import com.lazytravel.journey.dao.TourGroupServiceImpl;
import com.lazytravel.journey.entity.TourGroup;

@WebServlet(name = "JourneySelectServlet", value = "/journey/user/journeySelect.do")
public class JourneySelectServlet extends HttpServlet{

	private TourGroupService tourGroupSvc;
	
	@Override
	public void init() throws ServletException {
		tourGroupSvc = new TourGroupServiceImpl();
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
			case "journeySelect_order":
				forwardPath = toOrderPage(req, res);
				break;
			case "includeFragment":
				forwardPath = includeFragment(req, res);
				break;
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}


	private String toOrderPage(HttpServletRequest req, HttpServletResponse res) {	
		String groupId  = req.getParameter("groupId");
		Integer signupNum = Integer.valueOf(req.getParameter("signupNum"));
		
		// 送資料
		HttpSession session = req.getSession();
		session.setAttribute("groupId", groupId); 
		session.setAttribute("signupNum", signupNum); 
		
		return "/order/checkOut.html";
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
}
