package com.lazytravel.journey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.journey.dao.ShoppingCartService;
import com.lazytravel.journey.dao.ShoppingCartServiceImpl;
import com.lazytravel.journey.dao.TourGroupService;
import com.lazytravel.journey.dao.TourGroupServiceImpl;
import com.lazytravel.journey.entity.JourneyDetail;
import com.lazytravel.journey.entity.ShoppingCart;
import com.lazytravel.journey.entity.TourGroup;
import com.lazytravel.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@WebServlet(name = "ShoppingCartServlet", value = "/journey/user/shoppingCart.do")
public class ShoppingCartServlet extends HttpServlet {

	private ShoppingCartService shoppingCartSvc;
	private TourGroupService tourGroupSvc;
	
	@Override
	public void init() throws ServletException {
		shoppingCartSvc = new ShoppingCartServiceImpl();
		tourGroupSvc = new TourGroupServiceImpl();
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
			case "shoppingCart_enter":
				forwardPath = enterToShoppingCart(req, res);
				break;
			case "shoppingCart_add":
				forwardPath = addJourneyToShoppingCart(req, res);
				break;
			case "shoppingCart_update":
				forwardPath = updateQuantityFromShoppingCart(req, res);
				break;
			case "shoppingCart_delete":
				forwardPath = deleteTourGroupFromShoppingCart(req, res);
				break;
			case "shoppingCart_order":
				forwardPath = toOrderPage(req, res);
				break;	
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	
	private String enterToShoppingCart(HttpServletRequest req, HttpServletResponse res) {
//		Customer customer = (Customer) req.getSession().getAttribute("customer");
//		String customerId = String.valueOf(customer.getCustomerId()) ;
		String customerId = "11001";
		
		Map<String, String> cartData = new HashMap<>();
		if(customerId != null) {
			cartData = shoppingCartSvc.getOneByCustomerIdReturnMap(customerId);
		}
		
		List<ShoppingCart> shoppingCartList = new ArrayList<>();
		List<TourGroup> tourGroupList = new ArrayList<TourGroup>();
		
		for(String groupIdStr : cartData.keySet()) {
	        ShoppingCart shoppingCart = new ShoppingCart();
	        shoppingCart.setCustomerId(customerId);
	        shoppingCart.setGroupId(groupIdStr);
	        shoppingCart.setQuantity(Integer.valueOf(cartData.get(groupIdStr)));
	        shoppingCartList.add(shoppingCart);
	        
	        // 取得購物車頁面的其他資料 (從資料庫取)
			// 可優化:把購物車頁面需要的變數再存成一個Hash (Group:groupId, field, value, field, value,...)
			Integer groupId = Integer.valueOf(groupIdStr);
			TourGroup tourGroup = tourGroupSvc.getOneTourGroup(groupId);
			tourGroupList.add(tourGroup);
		}
		
		req.setAttribute("shoppingCartList", shoppingCartList);
		req.setAttribute("tourGroupList", tourGroupList);
		
		return "/journey/user/journey_shoppingCart.jsp";		
	}

	private String addJourneyToShoppingCart(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	private String updateQuantityFromShoppingCart(HttpServletRequest req, HttpServletResponse res) {
//		Customer customer = (Customer) req.getSession().getAttribute("customer");
//		String customerId = String.valueOf(customer.getCustomerId()) ;
		String customerId = "11001";
		
		Integer index = Integer.valueOf(req.getParameter("index"));
		String groupIdName = "groupId_" + index;
		String quantityChangeName = "quantityChange" + index;	
		
//		Integer quantity = Integer.valueOf(req.getParameter("quantityChange"));
		
		
		
		
		
		
		
//		shoppingCartSvc.updateQuantityFromCart(shoppingCart);
		
		return null;
	}
	
	private String deleteTourGroupFromShoppingCart(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}

	private String toOrderPage(HttpServletRequest req, HttpServletResponse res) {
		return null;
	}







	
//        // 取得購物車資訊
//        String customerId = request.getParameter("customerId"); // 這裡根據實際情況取得用戶 ID
//        ShoppingCart cart = shoppingCartService.getShoppingCart(customerId); // 從後端服務獲取購物車資訊
//        request.setAttribute("cart", cart); // 將購物車資訊設定到 request 中
//        request.getRequestDispatcher("/cart.jsp").forward(request, response); // 轉發到 JSP 頁面顯示購物車
//
//        String productId = request.getParameter("productId");
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        String customerId = request.getParameter("customerId"); // 假設此參數從前端獲取
//
//        // 在後端服務處理新增商品到購物車的邏輯，這可能涉及對 Redis 的寫入操作
//        shoppingCartService.addToCart(customerId, productId, quantity);
//
//        // 重新導向到購物車頁面，顯示更新後的資訊
//        response.sendRedirect(request.getContextPath() + "/cart?customerId=" + customerId);
	
	
	
	
	
}
