package com.lazytravel.blog.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.customer.entity.Customer;
import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.blog.service.BlogLikeService;
import com.lazytravel.blog.service.BlogLikeServiceImpl;

@WebServlet(value = "/blog/bloglike/blogLike.do")
public class BlogLikeServlet extends HttpServlet{
		private BlogLikeService blogLikeService;
		
		@Override
		public void init() throws ServletException {
			blogLikeService = new BlogLikeServiceImpl();
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
	            default:
	                forwardPath = "/blog/bloglike/blogLike_select_page.jsp";
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
	        String str = req.getParameter("blogLikeId");
	        if (str == null || str.trim().isEmpty())
	            errorMsgs.add("請輸入按讚編號");
	        if (!errorMsgs.isEmpty())
	            return "/blog/bloglike/blogLike_select_page.jsp";
	      //------------------1.-------------------------//
	        Integer blogLikeId = null;
	        try {
	        	blogLikeId = Integer.valueOf(str);
	        } catch (Exception e) {
	            errorMsgs.add("留言ID格式不正確");
	        }
	        if (!errorMsgs.isEmpty())
	            return "/blog/bloglike/blogLike_select_page.jsp";
	      //------------------2.-------------------------//
	        blogLikeService = new BlogLikeServiceImpl();
	        BlogLike blogLike = blogLikeService.getBlogLikeByBlogLikeId(blogLikeId);
	        if (blogLike == null)
	            errorMsgs.add("查無資料");
	        if (!errorMsgs.isEmpty())
	            return "/blog/bloglike/blogLike_select_page.jsp";
	      //------------------3.-------------------------//
	        req.setAttribute("blogLike", blogLike);
	        return "/blog/bloglike/listOneBlogLike.jsp";
	    }
	 // 2.修改
	    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
	    	Integer blogLikeId = Integer.valueOf(req.getParameter("blogLikeId"));
	    	BlogLike blogLike = blogLikeService.getBlogLikeByBlogLikeId(blogLikeId);
	    	
	    	 
	        req.setAttribute("blogLike", blogLike);
	        return "/blog/bloglike/update_blogLike_input.jsp";
	    }

	    private String update(HttpServletRequest req, HttpServletResponse res) {
	        // 錯誤處理
	        List<String> errorMsgs = new ArrayList<>();
	        req.setAttribute("errorMsgs", errorMsgs);
	      //-------------------1.---------------------------------//
	        Integer blogLikeId = Integer.valueOf(req.getParameter("blogLikeId"));
	        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
	    	Timestamp createTime =Timestamp.valueOf(req.getParameter("createTime"));
	    	 Integer blogId =Integer.parseInt(req.getParameter("blogId"));
	    	String blogStatus = String.valueOf(req.getParameter("blog_status"));
	        

	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
	        BlogLike blogLike = new BlogLike();
	        blogLike.setBlogLikeId(blogLikeId);
	        blogLike.setBlogLikeStatus(blogStatus);
	        blogLike.setCreateTime(createTime);
	        
	        Blog blog =new Blog();
	        blog.setBlogId(blogId);
	        blogLike.setBlog(blog);
	        
	        Customer customer = new Customer();
	        customer.setCustomerId(customerId);
	        blogLike.setCustomer(customer);
	        

	        if (!errorMsgs.isEmpty()) {
	            req.setAttribute("blogLike", blogLike);
	            return "/blog/bloglike/update_blogLike_input.jsp";
	        }
	        //-------------------2.---------------------------------//
	        // 修改資料
	        blogLikeService.updateBlogLike(blogLike);
	        req.setAttribute("blogLike", blogLikeService.getBlogLikeByBlogLikeId(blogLikeId));

	        return "/blog/bloglike/listOneBlogLike.jsp";
	    }

	    private String insert(HttpServletRequest req, HttpServletResponse res) {
	        // 錯誤處理
	        List<String> errorMsgs = new ArrayList<>();
	        req.setAttribute("errorMsgs", errorMsgs);
	      //-------------------1.---------------------------------//
//	        Integer blogLikeId = Integer.valueOf(req.getParameter("blogLikeId"));
	        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
	    	Timestamp createTime =Timestamp.valueOf(req.getParameter("createTime"));
	    	 Integer blogId =Integer.parseInt(req.getParameter("blogId"));
	    	String blogStatus = String.valueOf(req.getParameter("blog_status"));
	        

	        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
	        BlogLike blogLike = new BlogLike();
	        
//	        blogLike.setBlogLikeId(blogLikeId);
	        blogLike.setBlogLikeStatus(blogStatus);
	        blogLike.setCreateTime(createTime);
	        
	        Blog blog =new Blog();
	        blog.setBlogId(blogId);
	        blogLike.setBlog(blog);
	        
	        Customer customer = new Customer();
	        customer.setCustomerId(customerId);
	        blogLike.setCustomer(customer);

	        if (!errorMsgs.isEmpty()) {
	            req.setAttribute("blogLike", blogLike);
	            return "/blog/bloglike/addBlogLike.jsp";
	        }

	        // 新增資料
	        blogLikeService.addBlogLike(blogLike);

	        return "/blog/bloglike/listAllBlogLike.jsp";
	    }
}
