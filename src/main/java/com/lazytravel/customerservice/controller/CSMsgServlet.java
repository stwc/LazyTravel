package com.lazytravel.customerservice.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.lazytravel.customerservice.entity.CSMessage;
import com.lazytravel.customerservice.service.CSMessageService;
import com.lazytravel.customerservice.service.CSMessageServiceImpl;

@WebServlet(name = "CSMsgServlet",value = "/customerService/getMessage")
public class CSMsgServlet extends HttpServlet {

	private CSMessageService csMessageService;
	private Gson gson = new Gson();
	
	@Override
	public void init() throws ServletException {
		csMessageService = new CSMessageServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String customerId = req.getParameter("customerId");
		System.out.println("get customerId by URL:"+customerId);
		if(customerId == null || customerId.equals("")){
			HttpSession session = req.getSession(false);
			customerId = String.valueOf(session.getAttribute("customerId") == null? "" :session.getAttribute("customerId"));
			System.out.println("get customerId from session:"+customerId);
		}
		if(customerId != null && !customerId.equals("")) {
			List<CSMessage> csMessageList = csMessageService.findByCustomerId(Integer.valueOf(customerId));
			
			String csMessageListString = gson.toJson(csMessageList);
			
			ServletOutputStream out = res.getOutputStream();
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			out.write(csMessageListString.getBytes(StandardCharsets.UTF_8));
			out.flush();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

}