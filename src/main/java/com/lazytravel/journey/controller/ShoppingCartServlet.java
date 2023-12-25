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
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		String customerId = String.valueOf(customer.getCustomerId()) ;
		
		getListAndreturnJsp(req, res, customerId);
		
		return "/journey/user/journey_shoppingCart.jsp";		
	}

	
	private String addJourneyToShoppingCart(HttpServletRequest req, HttpServletResponse res) {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		String customerId = String.valueOf(customer.getCustomerId()) ;

		String groupId  = req.getParameter("groupId");
		Integer signupNum = Integer.valueOf(req.getParameter("signupNum"));
		
		ShoppingCart shoppingCart = shoppingCartSvc.getOneByCustomerIdAndGroupId(customerId, groupId);
		if(shoppingCart == null) {
			ShoppingCart shoppingCartAdd = new ShoppingCart(customerId, groupId, signupNum);
			shoppingCartSvc.addCart(shoppingCartAdd);
		} else {
			Integer quantity = shoppingCart.getQuantity();
			Integer total = signupNum + quantity;
			
			if(total <= 5) {
				shoppingCart.setQuantity(total);
				shoppingCartSvc.updateQuantityFromCart(shoppingCart);
			} else {
				shoppingCart.setQuantity(5);
				shoppingCartSvc.updateQuantityFromCart(shoppingCart);
			}
		}
		
		getListAndreturnJsp(req, res, customerId);
		
		return "/journey/user/journey_shoppingCart.jsp";
	}

	
	private String updateQuantityFromShoppingCart(HttpServletRequest req, HttpServletResponse res) {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		String customerId = String.valueOf(customer.getCustomerId()) ;
		
		Integer index = Integer.valueOf(req.getParameter("loopIndex"));
		String groupIdName = "groupId_" + index;
		String quantityChangeName = "quantityChange_" + index;	
		
		String groupIdReqGet = req.getParameter(groupIdName);
		String quantityReqGet = req.getParameter(quantityChangeName);
		
		ShoppingCart shoppingCartChange = new ShoppingCart();
		shoppingCartChange.setCustomerId(customerId);
		shoppingCartChange.setGroupId(groupIdReqGet);
		shoppingCartChange.setQuantity(Integer.valueOf(quantityReqGet));
        shoppingCartSvc.updateQuantityFromCart(shoppingCartChange);
		
        getListAndreturnJsp(req, res, customerId);
        
		return "/journey/user/journey_shoppingCart.jsp";
	}
	
	
	private String deleteTourGroupFromShoppingCart(HttpServletRequest req, HttpServletResponse res) {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		String customerId = String.valueOf(customer.getCustomerId()) ;
		
		Integer index = Integer.valueOf(req.getParameter("loopIndex"));
		String groupIdName = "groupId_" + index;
		String groupIdReqGet = req.getParameter(groupIdName);
		
		shoppingCartSvc.deleteCart(customerId, groupIdReqGet);
		
		getListAndreturnJsp(req, res, customerId);
		
		return "/journey/user/journey_shoppingCart.jsp";
	}

	
	private String toOrderPage(HttpServletRequest req, HttpServletResponse res) {
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		String customerId = String.valueOf(customer.getCustomerId()) ;
		
		Integer index = Integer.valueOf(req.getParameter("loopIndex"));
		String groupIdName = "groupId_" + index;
		String groupIdReqGet = req.getParameter(groupIdName);
		
		shoppingCartSvc.deleteCart(customerId, groupIdReqGet);
		
		//?????????????????????????送資料還沒寫
		
		return "/order/checkOut.html";
	}
	
	
	private void getListAndreturnJsp(HttpServletRequest req, HttpServletResponse res, String customerId) {
		Map<String, String> cartData = new HashMap<>();
		if(customerId != null) {
			cartData = shoppingCartSvc.getAllByCustomerIdReturnMap(customerId);
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
	}

}
