package com.lazytravel.order.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.lazytravel.journey.dao.JourneyService;
import com.lazytravel.journey.dao.JourneyServiceImpl;
import com.lazytravel.journey.dao.TourGroupService;
import com.lazytravel.journey.dao.TourGroupServiceImpl;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.TourGroup;
import com.lazytravel.order.dao.OrderDetailsResponseData;
import com.lazytravel.order.entity.Orders;
import com.lazytravel.order.service.OrdersService;

@WebServlet(name = "OrdersServlet", urlPatterns = {"/order/order.do" , "/admin/order.do"})
public class OrdersServlet extends HttpServlet {
	private OrdersService ordersService;
	private TourGroupService tourgroupService;
	private JourneyService journeyService;

	@Override
	public void init() throws ServletException {
		ordersService = new OrdersService();
		tourgroupService = new TourGroupServiceImpl();
		journeyService = new JourneyServiceImpl();
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
		
		case "getall":
              String jsonStr = new Gson().toJson(ordersService.getAll());
              res.setContentType("application/json; charset=UTF-8");
              res.getWriter().write(jsonStr);
              return;
              
		case "cancelorder":
			cancelorder(req , res);
			return;
              

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

		
		case "getJourneyNameByOrderId" :
			getJourneyNameByOrderId(req, res);
			return;
			
		case "getOrderByCustomerId" :
			getOrderByCustomerId(req , res);
			return;
			
		case "getOrderDetails" :
			getOrderDetails(req , res);
			return;

		default:
			forwardPath = "/order/select_page.jsp";
		}

		res.setContentType("text/html charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}
	
	
	
	
	
	private void getOrderDetails(HttpServletRequest req, HttpServletResponse res) {
		try {
			Integer groupId = Integer.parseInt(req.getParameter("groupId"));
			System.out.println(groupId);
			TourGroup tourgroup = tourgroupService.getOneTourGroup(groupId);
			System.out.println(tourgroup);

			List<OrderDetailsResponseData> responseDataList = new ArrayList<>();
			Journey journey = tourgroup.getJourney();
			System.out.println(journey);

			OrderDetailsResponseData responseData = new OrderDetailsResponseData();
			responseData.setJourneyName(journey.getJourneyName());
			responseData.setPrice(tourgroup.getPrice());
			responseData.setStartTime(tourgroup.getStartTime());
			responseData.setEndTime(tourgroup.getEndTime());
			
			responseDataList.add(responseData);
			
			System.out.println(responseDataList);
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			String dateFormatPattern = "yyyy" + " 年 " + "MM" +" 月 " + "dd" + " 日 ";
			gsonBuilder.setDateFormat(dateFormatPattern);
			

			
			Gson gson = gsonBuilder.create();
	        String jsonResponse = gson.toJson(responseDataList);
	     // 設置響應類型為 JSON
	        res.setContentType("application/json; charset=UTF-8");	
	        res.getWriter().write(jsonResponse);
			
		} catch (Exception e) {
			try {
	            res.setContentType("application/json; charset=UTF-8");
	            res.getWriter().write("{\"error\": \"Error processing request\"}");
	        } catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
		}
	}

	private void cancelorder(HttpServletRequest req, HttpServletResponse res) {
		Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		ordersService.cancelOrder(orderId);
		
		// 傳回取消成功的回應給前端
	    Gson gson = new Gson();
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("success", true);
	    
	    System.out.println(jsonResponse);
	    
	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    PrintWriter out;
		try {
			out = res.getWriter();
			out.print(gson.toJson(jsonResponse));
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	private void getJourneyNameByOrderId(HttpServletRequest req, HttpServletResponse res) {
		try {
			Integer orderId = Integer.parseInt(req.getParameter("orderId"));
			String journeyName = ordersService.getJourneyNameByOrderId(orderId);
			
			// 使用 Gson 格式化數據
	        Gson gson = new Gson();
	        String jsonResponse = gson.toJson(journeyName);
	        
	     // 設置響應類型為 JSON
	        res.setContentType("application/json; charset=UTF-8");	
	        res.getWriter().write(jsonResponse);
		} catch (Exception e) {
			try {
	            res.setContentType("application/json; charset=UTF-8");
	            res.getWriter().write("{\"error\": \"Error processing request\"}");
	        } catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
		}
	}
	
	private void getOrderByCustomerId(HttpServletRequest req, HttpServletResponse res) {
		try {
			Integer customerId = Integer.parseInt(req.getParameter("customerId"));
			List<Orders> orders = ordersService.getOrderByCustomerId(customerId);
			Gson gson = new Gson();
	        String jsonResponse = gson.toJson(orders);
	     // 設置響應類型為 JSON
	        res.setContentType("application/json; charset=UTF-8");	
	        res.getWriter().write(jsonResponse);
	        
			
		} catch (Exception e) {
			try {
	            res.setContentType("application/json; charset=UTF-8");
	            res.getWriter().write("{\"error\": \"Error processing request\"}");
	        } catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
		}
		
		
		
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
		String orderStatus = req.getParameter("orderStatus");
		String orderId = req.getParameter("orderId");

		Orders order = ordersService.getOneOrder(Integer.parseInt(orderId));
	    order.setOrderStatus(orderStatus);
	    ordersService.updateOrder(order);

	    req.setAttribute("order", order);
	    return "/admin/orderList.html"; // 这里可以根据你的业务逻辑进行跳转
	
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
		Integer groupId = Integer.valueOf(req.getParameter("group_id"));
		Integer tourist = Integer.valueOf(req.getParameter("tourist"));
		Integer customerPoint = Integer.valueOf(req.getParameter("customer_point"));
		Integer couponId = Integer.valueOf(req.getParameter("coupon_id"));
		Integer totalAmt = Integer.valueOf(req.getParameter("total_amt"));
		String orderStatus = String.valueOf(req.getParameter("order_status"));

		Orders order = new Orders();

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
		
		String orderNo = order.getOrderNo();
	    req.setAttribute("orderNo", orderNo);
		;
		return "/order/listAllEmp.jsp";

	}
	
	private boolean updateOrderStatus(String orderId, int newStatus) {
	    // 執行資料庫更新操作，將訂單狀態更新為 newStatus
	    // 如果成功，返回true，否則返回false
	    // 在實際應用中，您需要使用您的資料庫訪問代碼來執行這個操作
	    return true; // 這裡假設操作總是成功的
	}

}


