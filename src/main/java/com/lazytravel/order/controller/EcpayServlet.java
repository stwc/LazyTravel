package com.lazytravel.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.order.entity.Orders;
import com.lazytravel.order.service.CustomerCouponService;
import com.lazytravel.order.service.OrdersService;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@WebServlet(name = "EcpayServlet" , urlPatterns =  {"/order/ecpay.do" , "/customerCenter/ecpay.do"})
public class EcpayServlet extends HttpServlet{

	private OrdersService orderService ;

	public AllInOne all;
	
	@Override
	public void init() throws ServletException{
		orderService = new OrdersService();
		all = new AllInOne("");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		System.out.println("ecpay進來囉!");

		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		Orders order = orderService.getOneOrder(orderId);
		String orderNo = order.getOrderNo();
		Integer couponId = order.getCouponId();
		String totalAmt = String.valueOf(order.getTotalAmt());
		Timestamp tradeDate= order.getCreateTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formattedTradeDate = dateFormat.format(tradeDate);
		String itemName = orderService.getJourneyNameByOrderId(orderId);
		String customerId = String.valueOf(order.getCustomerId());
		int merchantTradeNoSet = 333 + orderId;

	
		System.out.println(orderId);
		System.out.println(orderNo);
		System.out.println(totalAmt);
		System.out.println(itemName);
		System.out.println(customerId);
	
		
		// 呼叫綠界
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo("LZY" + merchantTradeNoSet );
		obj.setMerchantTradeDate(formattedTradeDate);
		obj.setTotalAmount(totalAmt);
		obj.setTradeDesc("LazyTravel訂單第" + orderNo + "號" );
		obj.setItemName(itemName);
		obj.setCustomField1(customerId);
		obj.setCustomField2(String.valueOf(orderId));
		obj.setCustomField3(String.valueOf(couponId));
		obj.setReturnURL(" http://lazytravel.ddns.net./LazyTravel/order/ecpayreturn.do");
		obj.setOrderResultURL("http://lazytravel.ddns.net./LazyTravel/order/payComplete.jsp");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		
		System.out.println(form);
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html");
		try(PrintWriter out = res.getWriter()){
			out.print(form);
		}
	}
		
	
}
