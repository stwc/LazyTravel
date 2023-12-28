package com.lazytravel.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.order.entity.Orders;
import com.lazytravel.order.service.OrdersService;

import ecpay.payment.integration.AllInOne;

@WebServlet(name = "EcpayReturnServlet" , urlPatterns =  "/order/ecpayreturn.do")
public class EcpayReturnServlet extends HttpServlet{
	
	public static  AllInOne all;
	private OrdersService orderSvc;
	
	public void init() throws ServletException {
		all = new AllInOne("");
		orderSvc = new OrdersService();
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
		String customerId = req.getParameter("CustomField1");
		Integer orderId = Integer.parseInt(req.getParameter("CustomField2"));
		
		System.out.println(merchantTradeNo + " " + RtnMsg + " RtnCode=" + rtnCode + "customerId=" + customerId);
		
		
		if ("1".equals(rtnCode)) {
			Orders order = orderSvc.getOneOrder(orderId);
			order.setOrderStatus("1");
			orderSvc.updateOrder(order);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
}