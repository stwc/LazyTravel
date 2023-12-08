//package com.lazytravel.order.controller;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.lazytravel.order.service.CouponService;
//
//@WebServlet(name = "CouponServlet" , value = "/order/coupon.do")
//public class CouponServlet extends HttpServlet{
//	
//	private CouponService couponservice;
//	
//	
//	@Override
//	public void init() throws ServletException{
//		couponservice = new CouponService();
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		
//		String action = req.getParameter("action");
//		String forwardPath = "";
//		
//		switch (action) {
//		
//		case "getall" :
//			String jsonStr = new Gson().toJson(couponservice.getAll());
//			res.setContentType("application/json; charset=UTF-8");
//			res.getWriter().write(jsonStr);
//			return;
//		
//		case "getOne_For_Display":
//			// // 來自select_page.jsp的請求
//			forwardPath = getOneDisplay(req, res);
//			break;
//			
//			
//		case "insert":
//			forwardPath = insert(req, res);
//			break;
//			
//		case "delete":
//			forwardPath = delete(req, res);
//			break;
//		
//		default:
//			forwardPath = "XXX";
//		}
//		
//		res.setContentType("text/html charset=UTF-8");
//		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
//		dispatcher.forward(req, res);
//	}
//	
//	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		
//		String str = req.getParameter("coupon_id");
//		
//		if (str == null || str.trim().isEmpty())
//			errorMsgs.add("請輸入正確訂單ID");
//		if (!errorMsgs.isEmpty())
//			return "/order/select_page.jsp";
//	}
//	
//	
//	
//}
//	
