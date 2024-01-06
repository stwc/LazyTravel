package com.lazytravel.order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lazytravel.order.entity.Coupon;
import com.lazytravel.order.service.CouponService;

@WebServlet(name = "CouponServlet" , urlPatterns = {"/order/coupon.do", "/admin/coupon.do"})
public class CouponServlet extends HttpServlet{
	
	private CouponService couponservice;
	
	
	@Override
	public void init() throws ServletException{
		couponservice = new CouponService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		String forwardPath = "";
		
		switch (action) {
		
		case "getall" :
			String jsonStr = new Gson().toJson(couponservice.getAll());
			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(jsonStr);
			return;
			
		case  "update" :
			forwardPath = update(req , res);
			break;

		case "insert":
			forwardPath = insert(req, res);
			break;
			

		default:
			forwardPath = "XXX";
		}
		
		res.setContentType("text/html charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	private String update(HttpServletRequest req , HttpServletResponse res ) {
		
		String couponStatus = req.getParameter("couponStatus");
		String couponId = req.getParameter("couponId");
		
		Coupon coupon  = couponservice.getOneCoupon(Integer.parseInt(couponId));
		coupon.setCouponStatus(couponStatus);
		couponservice.updateCoupon(coupon);
		
		req.setAttribute("coupon", coupon);
		return "/admin/couponList.jsp";
	}
	
	
	private String insert (HttpServletRequest req , HttpServletResponse res)  {
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		String setialNo = req.getParameter("serialNo");
		String couponName = req.getParameter("couponName");
		Integer discount = Integer.valueOf(req.getParameter("discount"));
		Integer threshold = Integer.valueOf(req.getParameter("threshold"));	
		Integer total =  Integer.valueOf(req.getParameter("total"));
		
		Timestamp startTime = null;
	    Timestamp endTime = null;
		String startTimeStr = req.getParameter("startTime");
		String endTimeStr = req.getParameter("endTime");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date parsedStartDate;
		Date parsedEndDate;
		try {
			parsedStartDate = dateFormat.parse(startTimeStr);
			parsedEndDate = dateFormat.parse(endTimeStr);
			startTime = new Timestamp(parsedStartDate.getTime());
			endTime = new Timestamp(parsedEndDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		
		Coupon coupon = new Coupon();
		coupon.setSerialNo(setialNo);
		coupon.setCouponName(couponName);
		coupon.setDiscount(discount);
		coupon.setThreshold(threshold);
		coupon.setStartTime(startTime);
		coupon.setEndTime(endTime);
		coupon.setStock(total);
		coupon.setTotal(total);
		
		
		
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("coupon", coupon);
			return "/admin/couponAdd.jsp";
		}
		couponservice.addCoupon(coupon);
		return "/admin/couponList.jsp";
	}
	
	
}
	
