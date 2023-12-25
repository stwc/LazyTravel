package com.lazytravel.journey.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "JourneySelectServlet", value = "/journey/user/journeySelect.do")
public class JourneySelectServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
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
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	
	private String toOrderPage(HttpServletRequest req, HttpServletResponse res) {	
		String groupId  = req.getParameter("groupId");
		Integer signupNum = Integer.valueOf(req.getParameter("signupNum"));
		
		req.setAttribute("groupId", groupId);
		req.setAttribute("signupNum", signupNum);
		//?????????????????????????送資料還沒寫

		return "/order/checkOut.html";
	}
}
