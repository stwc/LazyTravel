package com.lazytravel.order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.example.dao.CustomerService;
import com.lazytravel.example.entity.Customer;
import com.lazytravel.order.entity.Orders;
import com.lazytravel.order.service.OrdersService;

@WebServlet(name = "OrdersServlet", value = "/order/order.do")
public class OrdersServlet extends HttpServlet {
	private OrdersService ordersService;

	@Override
	public void init() throws ServletException {
		ordersService = new OrdersService();
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
		case "getOne_For_Display":
			// // 來自select_page.jsp的請求
			forwardPath = getOneDisplay(req, res);
			break;

		case "getOne_By_OrderNo":
			forwardPath = getByOrderNo(req, res);
			break;

		case "getOne_For_Update":
			// 來自listAllEmp.jsp的請求
			forwardPath = getOneUpdate(req, res);
			break;

		case "update":
			forwardPath = update(req, res);
			break;

		case "insert":
			forwardPath = insert(req, res);
			break;

		default:
			forwardPath = "/order/select_page.jsp";
		}

		res.setContentType("text/htmll charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}

	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		String str = req.getParameter("order_id");
		if (str == null || str.trim().isEmpty())
			errorMsgs.add("請輸入正確訂單ID");
		if (!errorMsgs.isEmpty())
			return "/order/select_page.jsp";

		Integer orderId = null;
		try {
			orderId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("訂單ID格式不正確");
		}
		if (!errorMsgs.isEmpty())
			return "/order/select_page.jsp";

		OrdersService orderService = new OrdersService();
		Orders order = orderService.getOneOrder(orderId);
		if (order == null)
			errorMsgs.add("查無資料");
		if (!errorMsgs.isEmpty())
			return "/order/select_page.jsp";

		req.setAttribute("order", order);
		return "/order/listOneEmp.jsp";
	}

	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer orderId = Integer.valueOf(req.getParameter("order_id"));

		OrdersService orderService = new OrdersService();
		Orders order = orderService.getOneOrder(orderId);

		req.setAttribute("order", order);
		return "/order/update_emp_input.jsp";
	}

	private String getByOrderNo(HttpServletRequest req, HttpServletResponse res) {
		// 獲取輸入的order_no
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);

		String str = req.getParameter("order_no");
		if (str == null || str.trim().isEmpty())
			errorMsgs.add("請輸入正確訂單ID");
		if (!errorMsgs.isEmpty())
			return "/order/select_page.jsp";

		Integer orderNo = null;
		try {
			orderNo = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("訂單ID格式不正確");
		}
		if (!errorMsgs.isEmpty())
			return "/order/select_page.jsp";

		// 調用Service中的方法獲取訂單資訊
		OrdersService ordersService = new OrdersService();
		Orders order = ordersService.getOrderByOrderNo(orderNo);
		if (order == null)
			errorMsgs.add("查無資料");
		if (!errorMsgs.isEmpty())
			return "/order/select_page.jsp";

		// 將訂單資訊設置到request中，轉發到顯示訂單的頁面
		req.setAttribute("order", order);
		return "/order/listOneEmp.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {

		List<String> errorMsgs = new ArrayList<>();
		Integer orderId = Integer.valueOf(req.getParameter("order_id"));
		Integer orderNo = Integer.valueOf(req.getParameter("order_no"));
		Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
		Integer groupId = Integer.valueOf(req.getParameter("group_id"));
		Integer tourist = Integer.valueOf(req.getParameter("tourist"));
		Integer customerPoint = Integer.valueOf(req.getParameter("customer_point"));
		Integer couponId = Integer.valueOf(req.getParameter("coupon_id"));
		Integer totalAmt = Integer.valueOf(req.getParameter("total_amt"));
		String orderStatus = String.valueOf(req.getParameter("order_status"));

		Orders order = new Orders();
		order.setOrderId(orderId);
		order.setOrderNo(orderNo);
		order.setCustomerId(customerId);
		order.setGroupId(groupId);
		order.setTourist(tourist);
		order.setCustomerPoint(customerPoint);
		order.setCouponId(couponId);
		order.setTotalAmt(totalAmt);
		order.setOrderStatus(orderStatus);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("order", order);
			return "/example/addEmp.jsp";
		}

		ordersService.updateOrder(order);

		req.setAttribute("order", ordersService.getOneOrder(orderId));

		return "/order/listOneEmp.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new ArrayList<>();
		Integer orderNo = Integer.valueOf(req.getParameter("order_no"));
		Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
		Integer groupId = Integer.valueOf(req.getParameter("group_id"));
		Integer tourist = Integer.valueOf(req.getParameter("tourist"));
		Integer customerPoint = Integer.valueOf(req.getParameter("customer_point"));
		Integer couponId = Integer.valueOf(req.getParameter("coupon_id"));
		Integer totalAmt = Integer.valueOf(req.getParameter("total_amt"));
		String orderStatus = String.valueOf(req.getParameter("order_status"));

		Orders order = new Orders();
		order.setOrderNo(orderNo);
		order.setCustomerId(customerId);
		order.setGroupId(groupId);
		order.setTourist(tourist);
		order.setCustomerPoint(customerPoint);
		order.setCouponId(couponId);
		order.setTotalAmt(totalAmt);
		order.setOrderStatus(orderStatus);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("order", order);
			return "/example/addEmp.jsp";
		}

		ordersService.addOrder(order);
		;
		return "/order/listAllEmp.jsp";

	}

}
