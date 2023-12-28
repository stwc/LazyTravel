package com.lazytravel.blog.controller;

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

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.blog.service.BlogClService;
import com.lazytravel.blog.service.BlogClServiceImpl;
import com.lazytravel.blog.service.BlogLikeService;
import com.lazytravel.blog.service.BlogLikeServiceImpl;
import com.lazytravel.customer.entity.Customer;

@WebServlet(value = "/blog/blogcl/blogCl.do")
public class BlogClServlet extends HttpServlet{
		private BlogClService blogClService;
		
		@Override
		public void init() throws ServletException {
			blogClService = new BlogClServiceImpl();
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
	            case "getOne_For_Update":
	                // 來自listAllEmp.jsp的請求
	                forwardPath = getOneUpdate(req, res);
	                break;
	            case "update":
	                // 來自update_emp_input.jsp的請求
	                forwardPath = update(req, res);
	                break;
	            case "insert":
	                // 來自addEmp.jsp的請求
	                forwardPath = insert(req, res);
	                break;
	            case "onFavorite":
	                // 來自addEmp.jsp的請求
	                forwardPath = onFavorite(req, res);
	                break;
	            default:
	                forwardPath = "/blog/blogcl/blogCl_select_page.jsp";
	        }

	        res.setContentType("text/html; charset=UTF-8");
	        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
	        dispatcher.forward(req, res);
	    }
		//查詢
	    private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
	        // 錯誤處理
	        List<String> errorMsgs = new ArrayList<>();
	        req.setAttribute("errorMsgs", errorMsgs);
	      //----------------------------------------------------//
	        String str = req.getParameter("blogClId");
	        if (str == null || str.trim().isEmpty())
	            errorMsgs.add("請輸入收藏編號");
	        if (!errorMsgs.isEmpty())
	            return "/blog/blogcl/blogCl_select_page.jsp";
	      //------------------1.-------------------------//
	        Integer blogClId = null;
	        try {
	        	blogClId = Integer.valueOf(str);
	        } catch (Exception e) {
	            errorMsgs.add("留言ID格式不正確");
	        }
	        if (!errorMsgs.isEmpty())
	            return "/blog/blogcl/blogCl_select_page.jsp";
	      //------------------2.-------------------------//
	        blogClService = new BlogClServiceImpl();
	        BlogCl blogCl = blogClService.getBlogClByBlogClId(blogClId);
	        if (blogCl == null)
	            errorMsgs.add("查無資料");
	        if (!errorMsgs.isEmpty())
	            return "/blog/blogcl/blogCl_select_page.jsp";
	      //------------------3.-------------------------//
	        req.setAttribute("blogCl", blogCl);
	        return "/blog/blogcl/listOneBlogCl.jsp";
	    }
	 // 2.修改
	    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
	    	Integer blogClId = Integer.valueOf(req.getParameter("blogClId"));
	    	BlogCl blogCl = blogClService.getBlogClByBlogClId(blogClId);
	    	
	    	 
	        req.setAttribute("blogCl", blogCl);
	        return "/blog/blogcl/update_blogCl_input.jsp";
	    }

	    private String update(HttpServletRequest req, HttpServletResponse res) {
	        // 錯誤處理
	        List<String> errorMsgs = new ArrayList<>();
	        req.setAttribute("errorMsgs", errorMsgs);
	      //-------------------1.---------------------------------//
	        Integer blogClId = Integer.valueOf(req.getParameter("blogClId"));
	        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
	    	Timestamp likeTime =Timestamp.valueOf(req.getParameter("likeTime"));
	    	 Integer blogId =Integer.parseInt(req.getParameter("blogId"));
	    	String blogClStatus = String.valueOf(req.getParameter("blogClstatus"));
	        

	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
	        BlogCl blogCl = new BlogCl();
	        blogCl.setBlogClId(blogClId);
	        blogCl.setBlogClStatus(blogClStatus);
	        blogCl.setLikeTime(likeTime);
	        
	        Blog blog =new Blog();
	        blog.setBlogId(blogId);
	        blogCl.setBlog(blog);
	        
	        Customer customer = new Customer();
	        customer.setCustomerId(customerId);
	        blogCl.setCustomer(customer);
	        

	        if (!errorMsgs.isEmpty()) {
	            req.setAttribute("blogCl", blogCl);
	            return "/blog/blogcl/update_blogCl_input.jsp";
	        }
	        //-------------------2.---------------------------------//
	        // 修改資料
	        blogClService.updateBlogCl(blogCl);
	        req.setAttribute("blogCl", blogClService.getBlogClByBlogClId(blogClId));

	        return "/blog/blogcl/listOneBlogCl.jsp";
	    }

	    private String insert(HttpServletRequest req, HttpServletResponse res) {
	        // 錯誤處理
	        List<String> errorMsgs = new ArrayList<>();
	        req.setAttribute("errorMsgs", errorMsgs);
	      //-------------------1.---------------------------------//
	        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
	    	Timestamp likeTime =Timestamp.valueOf(req.getParameter("likeTime"));
	    	 Integer blogId =Integer.parseInt(req.getParameter("blogId"));
	    	String blogClStatus = String.valueOf(req.getParameter("blogClstatus"));
	        

	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
	        BlogCl blogCl = new BlogCl();
	        
	        blogCl.setLikeTime(likeTime);
	        blogCl.setBlogClStatus(blogClStatus);
	        
	        Blog blog =new Blog();
	        blog.setBlogId(blogId);
	        blogCl.setBlog(blog);
	        
	        Customer customer = new Customer();
	        customer.setCustomerId(customerId);
	        blogCl.setCustomer(customer);

	        if (!errorMsgs.isEmpty()) {
	            req.setAttribute("blogCl", blogCl);
	            return "/blog/blogcl/addBlogCl.jsp";
	        }

	        // 新增資料
	        blogClService.addBlogCl(blogCl);

	        return "/blog/blogcl/listAllBlogCl.jsp";
	    }

	    private String onFavorite(HttpServletRequest req, HttpServletResponse res) {
//	    	Integer customerId =Integer.valueOf(req.getParameter("customerId"));
//	    	Integer blogId = Integer.valueOf(req.getParameter("blogId"));
//	    	
//	    	boolean isBlogCl = blogClService.isBlogCl(customerId, blogId);
//	    	if (isBlogCl) {
//	            // 如果已經收藏，執行取消收藏
//	            blogClService.unFavoriteCl(customerId, blogId);
//	        } else {
//	            // 如果未收藏，執行收藏
//	        	blogClService.addFavoriteCl(customerId, blogId);
//	        }
			  return "1";
	    }
}

