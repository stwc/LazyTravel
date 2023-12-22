//package com.lazytravel.blog.controller;
//
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.lazytravel.blog.entity.Blog;
//import com.lazytravel.blog.entity.BlogCl;
//import com.lazytravel.blog.entity.BlogTag;
//import com.lazytravel.blog.entity.BlogTag.CompositeDetail;
//import com.lazytravel.blog.service.BlogClService;
//import com.lazytravel.blog.service.BlogClServiceImpl;
//import com.lazytravel.blog.service.BlogImgService;
//import com.lazytravel.blog.service.BlogImgServiceImpl;
//import com.lazytravel.blog.service.BlogTagService;
//import com.lazytravel.blog.service.BlogTagServiceImpl;
//import com.lazytravel.customer.entity.Customer;
//
//@WebServlet(value = "/blog/blogtag/blogTag.do")
//public class BlogTagServlet extends HttpServlet{
//		private BlogTagService blogTagService;
//
//		@Override
//		public void init() throws ServletException {
//			blogTagService = new BlogTagServiceImpl();
//		}
//
//		@Override
//		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			doPost(req, res);
//		}
//
//		@Override
//		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			req.setCharacterEncoding("UTF-8");
//
//			String action = req.getParameter("action");
//	        String forwardPath = "";
//	        switch (action) {
//	            case "getOne_For_Display":
//	                // // 來自select_page.jsp的請求
//	                forwardPath = getOneDisplay(req, res);
//	                break;
//	            case "getOne_For_Update":
//	                // 來自listAllEmp.jsp的請求
//	                forwardPath = getOneUpdate(req, res);
//	                break;
//	            case "update":
//	                // 來自update_emp_input.jsp的請求
//	                forwardPath = update(req, res);
//	                break;
//	            case "insert":
//	                // 來自addEmp.jsp的請求
//	                forwardPath = insert(req, res);
//	                break;
//	            case "delete":
//	            	 forwardPath = delete(req, res);
//	                 break;
//	            default:
//	                forwardPath = "/blog/blogtag/blogTag_select_page.jsp";
//	        }
//
//	        res.setContentType("text/html; charset=UTF-8");
//	        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
//	        dispatcher.forward(req, res);
//	    }
//		//查詢
//	    private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
//	        // 錯誤處理
//	        List<String> errorMsgs = new ArrayList<>();
//	        req.setAttribute("errorMsgs", errorMsgs);
//	      //----------------------------------------------------//
//	        String blogIdParam = req.getParameter("blogId");
//	        String tagIdParam = req.getParameter("tagId");
//	        if (blogIdParam == null || tagIdParam == null || blogIdParam.trim().isEmpty() || tagIdParam.trim().isEmpty()) {
//	            errorMsgs.add("請輸入收藏編號");
//	            return "/blog/blogtag/blogTag_select_page.jsp";
//	        }
//	            try {
//	            	Integer blogId = Integer.valueOf(blogIdParam);
//	                Integer tagId = Integer.valueOf(tagIdParam);
//	                CompositeDetail compositeDetail = new CompositeDetail(blogId, tagId);
//
//	                // 使用 BlogTagService 獲取 BlogTag
//	                blogTagService = new BlogTagServiceImpl();
//	                BlogTag blogTag = blogTagService.getBlogTagByBlogTagId(compositeDetail);
//
//	                if (blogTag == null) {
//	                    errorMsgs.add("查無資料");
//	                } else {
//	                    req.setAttribute("blogTag", blogTag);
//	                    return "/blog/blogtag/listOneBlogTag.jsp";
//	                }
//	            } catch (NumberFormatException e) {
//	                errorMsgs.add("留言ID格式不正確");
//	            }
//
//	        return "/blog/blogtag/blogTag_select_page.jsp";
//	    }
//
//	 // 2.修改
//	    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//	    	Integer blogId = Integer.valueOf(req.getParameter("blogId"));
//	    	Integer tagId = Integer.valueOf(req.getParameter("tagId"));
//	    	CompositeDetail compositeDetail = new CompositeDetail(blogId, tagId);
//	    	BlogTag blogTag = blogTagService.getBlogTagByBlogTagId(compositeDetail);
//	    	System.out.println("修改上"+blogTag);
//	    	System.out.println("修改上"+compositeDetail);
//
//	        req.setAttribute("blogTag", blogTag);
//
//	        return "/blog/blogtag/update_blogTag_input.jsp";
//	    }
//
//	    private String update(HttpServletRequest req, HttpServletResponse res) {
//	        // 錯誤處理
//	        List<String> errorMsgs = new ArrayList<>();
//	        req.setAttribute("errorMsgs", errorMsgs);
//	      //-------------------1.---------------------------------//
//	        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
//	        Integer tagId = Integer.valueOf(req.getParameter("tagId"));
//	        CompositeDetail compositeDetail = new CompositeDetail(blogId, tagId);
//	        BlogTag blogTag = blogTagService.getBlogTagByBlogTagId(compositeDetail);
//	        System.out.println("修改下"+blogTag);
//	        System.out.println("修改下"+compositeDetail);
//
//	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
////	        BlogTag blogTag = new BlogTag();
////	        blogTag.setBlogId(blogId);
////	        blogTag.setTagId(tagId);
//
//
//	        if (!errorMsgs.isEmpty()) {
//	            req.setAttribute("blogTag", blogTag);
//	            return "/blog/blogtag/update_blogTag_input.jsp";
//	        }
//	        //-------------------2.---------------------------------//
//	        // 修改資料
//	        blogTagService.updateBlogTag(compositeDetail);
//	        req.setAttribute("blogTag", blogTagService.getBlogTagByBlogTagId(compositeDetail));
//
//	        return "/blog/blogtag/listOneBlogTag.jsp";
//	    }
//
//	    private String insert(HttpServletRequest req, HttpServletResponse res) {
//	        // 錯誤處理
//	        List<String> errorMsgs = new ArrayList<>();
//	        req.setAttribute("errorMsgs", errorMsgs);
//	      //-------------------1.---------------------------------//
//	        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
//	        Integer tagId = Integer.valueOf(req.getParameter("tagId"));
//	        CompositeDetail compositeDetail = new CompositeDetail(blogId, tagId);
//
//
//	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
//	        BlogTag blogTag = new BlogTag();
//	        blogTag.setBlogId(blogId);
//	        blogTag.setTagId(tagId);
//
//	        if (!errorMsgs.isEmpty()) {
//	            req.setAttribute("compositeDetail", blogTag);
//	            return "/blog/blogtag/addBlogTag.jsp";
//	        }
//
//	        // 新增資料
//	        blogTagService.addBlogTag(compositeDetail);
//
//	        return "/blog/blogtag/listAllBlogTag.jsp";
//	    }
//	    private String delete(HttpServletRequest req, HttpServletResponse res) {
//	        // 錯誤處理
//	        List<String> errorMsgs = new ArrayList<>();
//	        req.setAttribute("errorMsgs", errorMsgs);
//
//	        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
//	        Integer tagId = Integer.valueOf(req.getParameter("tagId"));
//	        CompositeDetail compositeDetail = new CompositeDetail(blogId, tagId);
//
//	        BlogTag blogTag = new BlogTag();
//	        blogTag.setBlogId(blogId);
//	        blogTag.setTagId(tagId);
//
//
//	        if (!errorMsgs.isEmpty()) {
//	            req.setAttribute("blogTag", blogTag);
//	            return "/blog/blogtag/update_blogTag_input.jsp";
//
//
//	    }
//	        blogTagService.deleteBlogTag(compositeDetail);
//
//	        return "/blog/blogtag/listAllBlogTag.jsp";
//	    }
//}
//
//
