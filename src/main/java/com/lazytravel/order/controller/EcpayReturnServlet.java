package com.lazytravel.order.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.order.entity.CustomerCoupon;
import com.lazytravel.order.entity.Orders;
import com.lazytravel.order.service.CustomerCouponService;
import com.lazytravel.order.service.OrdersService;

import ecpay.payment.integration.AllInOne;

@WebServlet(name = "EcpayReturnServlet" , urlPatterns =  "/order/ecpayreturn.do")
public class EcpayReturnServlet extends HttpServlet{
	
	public static  AllInOne all;
	private OrdersService orderSvc;
	private CustomerCouponService customerSvc;
	
	public void init() throws ServletException {
		all = new AllInOne("");
		orderSvc = new OrdersService();
		customerSvc = new CustomerCouponService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		System.out.println("綠界回來囉!");
		
		String merchantID = req.getParameter("MerchantID");
		String merchantTradeNo = req.getParameter("MerchantTradeNo");
		String RtnMsg = req.getParameter("RtnMsg");
		String checkMacValue = req.getParameter("CheckMacValue");
		String rtnCode = req.getParameter("RtnCode");
		Integer customerId = Integer.parseInt(req.getParameter("CustomField1"));
		Integer orderId = Integer.parseInt(req.getParameter("CustomField2"));
		String couponIdstr = req.getParameter("CustomField3");
		Integer couponId = 0;
		if(!couponIdstr.equals("null")) {
			couponId = Integer.parseInt(couponIdstr);
		}else {
			couponId = 0;
		}
		
		System.out.println(merchantTradeNo + " " + RtnMsg + " RtnCode=" + rtnCode + "customerId=" + customerId);
		
		
		if ("1".equals(rtnCode)) {
			Orders order = orderSvc.getOneOrder(orderId);
			order.setOrderStatus("1");
			order.setPaidTime(new Timestamp(System.currentTimeMillis()));
			
			if(couponId != 0) {
				CustomerCoupon ccs = customerSvc.get1Customer1Coupon(customerId, couponId);
				ccs.setCouponStatus("1");
				customerSvc.updateCustomerCoupon(ccs);
			}
		
			
			orderSvc.updateOrder(order);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
}
