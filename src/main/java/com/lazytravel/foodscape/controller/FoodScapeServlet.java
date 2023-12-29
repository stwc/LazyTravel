package com.lazytravel.foodscape.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.service.FoodScapeService;
import com.lazytravel.foodscape.service.FoodScapeServiceImpl;

@WebServlet(name = "FoodScapeServlet", value = "/foodscape/jsp/FoodScape.do")
public class FoodScapeServlet extends HttpServlet {
	private FoodScapeService foodscapeService;

	@Override
	public void init() throws ServletException{
		foodscapeService = new FoodScapeServiceImpl();
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
		case "getOne_For_Display":
            forwardPath = getOneDisplay(req, res);
            break;
		case "foodscape_search":
			forwardPath = getFoodScapeByFoodScapeId(req, res);
			break;
		case "tag_add":
			forwardPath = addFoodScape(req, res);
		case "tag_update":
			forwardPath = updateFoodScape(req, res);
	}
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}

	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        Integer foodScapeId =Integer.valueOf(req.getParameter("foodScapeId"));
        
        foodscapeService = new FoodScapeServiceImpl();
        FoodScape foodscape = foodscapeService.getFoodScapeByFoodScapeId(foodScapeId);
        
        req.setAttribute("foodscape", foodscape);
        return "/foodscape/jsp/selectmore.jsp";

	}

	private String updateFoodScape(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
		
        Integer foodScapeId = Integer.valueOf(req.getParameter("FOODSCAPE_ID"));
        String foodScapeName = req.getParameter("foodscape_name").trim();
		String foodScapeNameReg = "[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
		if (foodScapeName == null || foodScapeName.length() == 0) {
			errorMsgs.add("請輸入美食景點名稱");
		} else if (!foodScapeName.matches(foodScapeNameReg)) {
			errorMsgs.add("名稱:格式不符合,僅限輸入中英文和數字共計20字");
		}

		String intro = req.getParameter("intro").trim();
		if (intro == null || intro.length() == 0) {
			errorMsgs.add("請輸入美食景點介紹");
		}

		String city = req.getParameter("city").trim();
		String cityReg = "[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,10}$";
		if (city == null || city.length() == 0) {
			errorMsgs.add("請輸入美食景點所在城市");
		} else if (!city.matches(cityReg)) {
			errorMsgs.add("名稱:格式不符合,僅限輸入中英文和數字共計10字");
		}

		String address = req.getParameter("address").trim();
		String addressReg = "[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,30}$";
		if (address == null || address.length() == 0) {
			errorMsgs.add("請輸入美食景點完整地址");
		} else if (!address.matches(cityReg)) {
			errorMsgs.add("名稱:格式不符合,僅限輸入中英文和數字共計30字");
		}
		
		String phone = req.getParameter("phone").trim();
		String phoneReg = "^[0-9]{8}([0-9]{2})?$";
		if (address == null || address.length() == 0) {
			errorMsgs.add("請輸入美食景點完整電話或手機號碼(不用-)");
		} else if (!address.matches(cityReg)) {
			errorMsgs.add("名稱:格式不符合,請輸入美食景點完整電話或手機號碼(不用-)");
		}
		
		String foodscapeStatus = String.valueOf(req.getParameter("foodscape_status").trim());
		String category = String.valueOf(req.getParameter("CATEGORY").trim());
//		Double lng = Double.valueOf(req.getParameter("LNG"));
//		Double lat = Double.valueOf(req.getParameter("LAT"));
//		String updateTimeStr = req.getParameter("UPDATE_TIME");

//	        if (updateTimeStr != null && !updateTimeStr.isEmpty()) {
//	            try {
//	                Timestamp updateTime = Timestamp.valueOf(updateTimeStr);
//	                // 在這裡使用 updateTime
//	                System.out.println("Update Time: " + updateTime);
//	            } catch (IllegalArgumentException e) {
//	                // 處理轉換失敗的情況
//	                e.printStackTrace();
//	            }
//	        } else {
//	            // 如果 UPDATE_TIME 參數為空，可能需要處理相應的邏輯
//	        }
	 	// 假如輸入格式錯誤的，備份選原使用者輸入過的資料
    		FoodScape foodscape = new FoodScape();
    		foodscape.setFoodScapeName(foodScapeName);
    		foodscape.setIntro(intro);
    		foodscape.setCity(city);
    		foodscape.setAddress(address);
    		foodscape.setPhone(phone);
    		foodscape.setFoodScapeStatus(foodscapeStatus);
    		foodscape.setCategory(category);
    		
	        if (!errorMsgs.isEmpty()) {
	        	req.setAttribute("foodscape", foodscape);
	        	return "/foodscape/jsp/foodscapeModify.jsp";
	        }
	        
	        //修改資料
	        foodscapeService.updateFoodScape(foodscape);
	        req.setAttribute("foodscape", foodscapeService.getFoodScapeByFoodScapeId(foodScapeId));
	        
			return "/foodscape/jsp/foodscapeModify.jsp";
	}
	
	
	private String getFoodScapeByFoodScapeId(HttpServletRequest req, HttpServletResponse res) {
		Integer foodScapeId = Integer.valueOf(req.getParameter("FOODSCAPE_ID"));
		
		FoodScape foodscape = foodscapeService.getFoodScapeByFoodScapeId(foodScapeId);
		
		req.setAttribute("foodscape", foodscape);
		return "/foodscape/jsp/foodscape.jsp";

	}

	private String addFoodScape(HttpServletRequest req, HttpServletResponse res) {
		//錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		String foodscapeName = req.getParameter("foodScapeName").trim();
		String foodscapeNameReg = "[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,20}$";
		if (foodscapeName == null || foodscapeName.trim().length() == 0) {
			errorMsgs.add("請輸入美食景點名稱");
		} else if (!foodscapeName.matches(foodscapeNameReg)) {
			errorMsgs.add("名稱:格式不符合,僅限輸入中英文和數字共計20字");
		}
		String intro = req.getParameter("intro").trim();
		if (intro == null || intro.length() == 0) {
			errorMsgs.add("請輸入美食景點介紹");
		}

		String city = req.getParameter("city").trim();
		String cityReg = "[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,10}$";
		if (city == null || city.length() == 0) {
			errorMsgs.add("請輸入美食景點所在城市");
		} else if (!city.matches(cityReg)) {
			errorMsgs.add("名稱:格式不符合,僅限輸入中英文和數字共計10字");
		}

		String address = req.getParameter("address").trim();
		String addressReg = "[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{1,30}$";
		if (address == null || address.length() == 0) {
			errorMsgs.add("請輸入美食景點完整地址");
		} else if (!address.matches(cityReg)) {
			errorMsgs.add("名稱:格式不符合,僅限輸入中英文和數字共計30字");
		}
		
		String phone = req.getParameter("phone").trim();
		String phoneReg = "^[0-9]{8}([0-9]{2})?$";
		if (address == null || address.length() == 0) {
			errorMsgs.add("請輸入美食景點完整電話或手機號碼(不用-)");
		} else if (!address.matches(cityReg)) {
			errorMsgs.add("名稱:格式不符合,請輸入美食景點完整電話或手機號碼(不用-)");
		}
		
		String foodscapeStatus = String.valueOf(req.getParameter("foodscape_status").trim());
		String category = req.getParameter("category");

		FoodScape foodscape = new FoodScape();
		foodscape.setFoodScapeName(foodscapeName);
		foodscape.setIntro(intro);
		foodscape.setCity(city);
		foodscape.setAddress(address);
		foodscape.setPhone(phone);
		foodscape.setFoodScapeStatus(foodscapeStatus);
		foodscape.setCategory(category);
		
		 if (!errorMsgs.isEmpty()) {
	        	req.setAttribute("foodscape", foodscape);
	        	return "/foodscape/jsp/foodscape_on.jsp";
	        }
	        
	        foodscapeService.addFoodScape(foodscape);
	        return "/foodscape/jsp/foodscape.jsp";
	    }
}