//package com.lazytravel.customerservice.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.Timestamp;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.json.JSONObject;
//
//import com.google.gson.Gson;
//import com.lazytravel.customerservice.CSMessageFrom;
//import com.lazytravel.customerservice.entity.CSMessage;
//import com.lazytravel.customerservice.service.CSMessageService;
//import com.lazytravel.customerservice.service.CSMessageServiceImpl;
//
//@WebServlet(name = "CSMsgSendServlet",value = "/customerService/customerSendMsg")
//public class CSMsgSendServlet extends HttpServlet {
//
//	private CSMessageService csMessageService;
//	private Gson gson = new Gson();
//	
//	@Override
//	public void init() throws ServletException {
//		csMessageService = new CSMessageServiceImpl();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession session = req.getSession(false);
//		String customerId = session.getAttribute("customerId").toString();
//		System.out.println("customerId:"+customerId);
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String json = "";
//        if (br != null) {
//            json = br.readLine();
//        }
//        
//        CSMessage csMessage = gson.fromJson(json, CSMessage.class);
//        csMessage.setMessageFrom(CSMessageFrom.CUSTOMER.getMessageFrom());
//        csMessage.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        csMessageService.saveCSMessage(csMessage, Integer.valueOf(customerId));
//		
//	}
//
//}