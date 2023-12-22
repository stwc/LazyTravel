package com.lazytravel.foodscape.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.entity.OpenTime;
import com.lazytravel.foodscape.entity.Tag;
import com.lazytravel.foodscape.service.OpenTimeService;
import com.lazytravel.foodscape.service.OpenTimeServiceImpl;

@WebServlet(name = "OpenTimeServlet", value = "/foodscape/opentime.do")
public class OpenTimeServlet extends HttpServlet{
	private OpenTimeService opentimeService;
	
	@Override
	public void init() throws ServletException{
		opentimeService = new OpenTimeServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action ) {
			case "opentime_search":
				forwardPath = getOpenTimeByOpenTimeId(req, res);
				break;
			case "opentime_add":
				forwardPath = addOpenTime(req, res);
			case "opentime_update":
				forwardPath = updateOpenTime(req, res);
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
		}

	private String updateOpenTime(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        Integer openTimeId = Integer.valueOf(req.getParameter("OPENTIME_ID"));
        FoodScape foodScapeId = FoodScape.valueOf(req.getParameter("FOODSCAPE_ID"));
        
        String startTimeStr = req.getParameter("START_TIME");
        if (startTimeStr == null || startTimeStr.isEmpty()) {
            errorMsgs.add("請輸入開店時間");
        } else {
            try {
                Time startTime = Time.valueOf(startTimeStr);
                // 根據需要使用 startTime
            } catch (IllegalArgumentException e) {
                // 處理輸入不是有效時間格式的情況
                errorMsgs.add("開店時間格式不正確");
            }
        }
        
        String endTimeStr = req.getParameter("END_TIME");
        if (endTimeStr == null || endTimeStr.isEmpty()) {
            errorMsgs.add("請輸入關店時間");
        } else {
            try {
                Time endTime = Time.valueOf(endTimeStr);
                // 根據需要使用 startTime
            } catch (IllegalArgumentException e) {
                // 處理輸入不是有效時間格式的情況
                errorMsgs.add("開店時間格式不正確");
            }
        }
        
        String week = req.getParameter("OPENTIME_WEEK");
        if (week == null || week.trim().isEmpty())
        	errorMsgs.add("請輸入星期一到日");
        
        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        OpenTime opentime = new OpenTime();
        opentime.setOpenTimeId(openTimeId);
        opentime.setFoodScapeId(foodScapeId) ;
        
        if (!errorMsgs.isEmpty()) {
        	req.setAttribute("opentime", opentime);
        	return "/foodscape/jsp/opentimeModify.jsp";
        }
        
        //修改資料
        opentimeService.updateOpenTime(opentime);
        req.setAttribute("tag", opentimeService.getOpenTimeByOpenTimeId(openTimeId));
        
		return "/foodscape/jsp/opentime.jsp";

	}

	private String addOpenTime(HttpServletRequest req, HttpServletResponse res) {
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        Integer openTimeId = Integer.valueOf(req.getParameter("OPENTIME_ID"));
        FoodScape foodScapeId = FoodScape.valueOf(req.getParameter("FOODSCAPE_ID"));
        
        String startTimeStr = req.getParameter("START_TIME");
        if (startTimeStr == null || startTimeStr.isEmpty()) {
            errorMsgs.add("請輸入開店時間");
        } else {
            try {
                Time startTime = Time.valueOf(startTimeStr);
                // 根據需要使用 startTime
            } catch (IllegalArgumentException e) {
                // 處理輸入不是有效時間格式的情況
                errorMsgs.add("開店時間格式不正確");
            }
        }
        
        String endTimeStr = req.getParameter("END_TIME");
        if (endTimeStr == null || endTimeStr.isEmpty()) {
            errorMsgs.add("請輸入關店時間");
        } else {
            try {
                Time endTime = Time.valueOf(endTimeStr);
                // 根據需要使用 startTime
            } catch (IllegalArgumentException e) {
                // 處理輸入不是有效時間格式的情況
                errorMsgs.add("開店時間格式不正確");
            }
        }
        
        String week = req.getParameter("OPENTIME_WEEK");
        if (week == null || week.trim().isEmpty())
        	errorMsgs.add("請輸入星期一到日");
        
        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        OpenTime opentime = new OpenTime();
        opentime.setOpenTimeId(openTimeId);
        opentime.setFoodScapeId(foodScapeId) ;
        opentime.setStartTime(startTimeStr);
        opentime.setEndTime(endTimeStr);
        
        if (!errorMsgs.isEmpty()) {
        	req.setAttribute("opentime", opentime);
        	return "/foodscape/jsp/opentime_on.jsp";
        }
        
        //修改資料
        opentimeService.updateOpenTime(opentime);
        req.setAttribute("tag", opentimeService.getOpenTimeByOpenTimeId(openTimeId));
        
		return "/foodscape/jsp/opentime.jsp";

	}

	private String getOpenTimeByOpenTimeId(HttpServletRequest req, HttpServletResponse res) {
		Integer openTimeId = Integer.valueOf(req.getParameter("OPENTIME_ID"));
		
		OpenTime opentime = opentimeService.getOpenTimeByOpenTimeId(openTimeId);
		
		req.setAttribute("opentime", opentime);
		return "/foodscape/jsp/opentime.jsp";
	}
	
}