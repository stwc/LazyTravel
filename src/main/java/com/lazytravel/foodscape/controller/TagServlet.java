package com.lazytravel.foodscape.controller;

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

import com.lazytravel.foodscape.entity.Tag;
import com.lazytravel.foodscape.service.TagService;
import com.lazytravel.foodscape.service.TagServiceImpl;


@WebServlet(name = "TagServlet", value = "/foodscape/tag.do")
public class TagServlet extends HttpServlet{
	private TagService tagService;
	
	@Override
	public void init() throws ServletException{
		tagService = new TagServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action ) {
			case "tag_search":
				forwardPath = getTagByTagId(req, res);
				break;
			case "tag_add":
				forwardPath = addTag(req, res);
			case "tag_update":
				forwardPath = updateTag(req, res);
		}
		
		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
		}
	
		private String updateTag(HttpServletRequest req, HttpServletResponse res) {
	        List<String> errorMsgs = new ArrayList<>();
	        req.setAttribute("errorMsgs", errorMsgs);
	        
	        String tagName = req.getParameter("TAG_NAME");
	        if (tagName == null || tagName.trim().isEmpty())
	        	errorMsgs.add("請輸入標籤名稱");
	        
	        Integer tagId = Integer.valueOf(req.getParameter("TAG_ID"));
	        String updateTimeStr = req.getParameter("UPDATE_TIME");

	        if (updateTimeStr != null && !updateTimeStr.isEmpty()) {
	            try {
	                Timestamp updateTime = Timestamp.valueOf(updateTimeStr);
	                // 在這裡使用 updateTime
	                System.out.println("Update Time: " + updateTime);
	            } catch (IllegalArgumentException e) {
	                // 處理轉換失敗的情況
	                e.printStackTrace();
	            }
	        } else {
	            // 如果 UPDATE_TIME 參數為空，可能需要處理相應的邏輯
	        }
	        
	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
	        Tag tag = new Tag();
	        tag.setTagId(tagId);
	        tag.setTagName(tagName);
	        
	        if (!errorMsgs.isEmpty()) {
	        	req.setAttribute("tag", tag);
	        	return "/foodscape/jsp/tagModify.jsp";
	        }
	        
	        //修改資料
	        tagService.updateTag(tag);
	        req.setAttribute("tag", tagService.getTagByTagId(tagId));
	        
			return "/foodscape/jsp/tag.jsp";
	}

		private String addTag(HttpServletRequest req, HttpServletResponse res) {
			//錯誤處理
			List<String> errorMsgs = new ArrayList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			//抓前端送來的參數
			Integer tagId = Integer.valueOf(req.getParameter("TAG_ID"));
			String tagName = req.getParameter("TAG_NAME");
			String updateTimeStr = req.getParameter("updateTime");

	        if (updateTimeStr != null && !updateTimeStr.isEmpty()) {
	            try {
	                // 將字串轉換為 Timestamp
	                Timestamp updateTime = Timestamp.valueOf(updateTimeStr);

	                // 在這裡使用 updateTime
	                System.out.println("Update Time: " + updateTime);
	            } catch (IllegalArgumentException e) {
	                // 處理轉換失敗的情況
	                e.printStackTrace();
	            }
	        } else {
	            // 如果 updateTime 參數為空，可能需要處理相應的邏輯
	        }
	        
	        // 假如輸入格式錯誤的，備份還原使用者輸入過的資料
	        Tag tag = new Tag();
	        tag.setTagName(tagName);
	        
	        //輸入資料錯誤,重新輸入
	        if (!errorMsgs.isEmpty()) {
	        	req.setAttribute("tag", tag);
	        	return "/foodscape/jsp/tag_on.jsp";
	        }
	        
	        tagService.addTag(tag);
	        return "/foodscape/jsp/tag.jsp";
	    }

		private String getTagByTagId(HttpServletRequest req, HttpServletResponse res) {
			Integer tagId = Integer.valueOf(req.getParameter("TAG_ID"));
			
			Tag tag = tagService.getTagByTagId(tagId);
			
			req.setAttribute("tag", tag);
			return "/foodscape/jsp/tag.jsp";

	}
}		

//		private String getAllTags(HttpServletRequest req, HttpServletResponse res) {
//			String page = req.getParameter("page");
//			int currentPage = (page == null) ? 1 : Integer.parseInt(page);
//			
//			List<Tag> tagList = tagService.getAllTags(currentPage);
//
////			if (req.getSession().getAttribute("tagPageQty") == null) {
////				int tagPageQty = tagService.getPageTotal();
////				req.getSession().setAttribute("empPageQty", empPageQty);
////			}
//			
//			req.setAttribute("tagList", tagList);
//			req.setAttribute("currentPage", currentPage);
//			
//			return "/emp/listAllEmps.jsp";
//		}
//		
////		private String getCompositeEmpsQuery(HttpServletRequest req, HttpServletResponse res) {
////			Map<String, String[]> map = req.getParameterMap();
//			
////			if (map != null) {
////				List<Tag> empList = tagService.getEmpsByCompositeQuery(map);
////				req.setAttribute("tagList", tagList);
////			} else {
////				return "/index.jsp";
////			}
////			return "/emp/listCompositeQueryEmps.jsp";
////		}
//		
//		
//		@Override
//		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			doPost(req, res);
//		}
//		public void destroy() {
//		    // 关闭数据库连接的代码
//		    // 例如，如果你使用了 Connection 对象，可以调用其 close 方法
//		    // connection.close();
//		    super.destroy();
//		}
//	}
