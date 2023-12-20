package com.lazytravel.blog.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmBaseVersionAttributeType;

import com.lazytravel.blog.dao.BlogDAO;
import com.lazytravel.blog.dao.BlogDAOImpl;
import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.service.BlogService;
import com.lazytravel.blog.service.BlogServiceImpl;
import com.lazytravel.customer.entity.Customer;

@WebServlet(value = "/blog/blog/blog.do")
public class BlogServlet extends HttpServlet {
	private BlogService blogService;

	@Override
	public void init() throws ServletException {
		blogService = new BlogServiceImpl();
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
                forwardPath = "/blog/blog/select_page.jsp";
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
        String str = req.getParameter("blogId");
        
        if (str == null || str.trim().isEmpty())
            errorMsgs.add("請輸入文章編號");
        if (!errorMsgs.isEmpty())
            return "/blog/blog/select_page.jsp";
//------------------1.-------------------------//
        Integer blogId = null;
        try {
            blogId = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("文章ID格式不正確");
        }
        if (!errorMsgs.isEmpty())
            return "/blog/blog/select_page.jsp";
        
      //------------------2.-------------------------//
        blogService = new BlogServiceImpl();
        Blog blog = blogService.getBlogByBlogId(blogId);
        if (blog == null)
            errorMsgs.add("查無資料");
        if (!errorMsgs.isEmpty())
            return "/blog/blog/select_page.jsp";

      //------------------3.-------------------------//
        req.setAttribute("blog", blog);
        return "/blog/blog/listOneBlog.jsp";
    }
// 2.修改
    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
        Blog blog = blogService.getBlogByBlogId(blogId);
        

        req.setAttribute("blog", blog);
        return "/blog/blog/update_blog_input.jsp";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
//-------------------1.---------------------------------//
        Integer blogId =Integer.parseInt(req.getParameter("blogId"));
        String title =String.valueOf(req.getParameter("title"));
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
        Timestamp blogDate =Timestamp.valueOf(req.getParameter("blog_date"));
        String content =String.valueOf(req.getParameter("content"));
        String blogStatus =String.valueOf(req.getParameter("blog_status"));
        
        Timestamp upDateTime =null;
        try {
     	   upDateTime = java.sql.Timestamp.valueOf(req.getParameter("upDateTime").trim());
 	} catch (Exception e) {
 		upDateTime =new java.sql.Timestamp(System.currentTimeMillis());
 		errorMsgs.add("請輸入更新時間");
 	}
        
        Timestamp createTime =null;
        try {
     	   createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
 	} catch (Exception e) {
 		createTime =new java.sql.Timestamp(System.currentTimeMillis());
 		errorMsgs.add("請輸入發布時間");
 	}

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setTitle(title);
        blog.setBlogDate(blogDate);
        blog.setContent(content);
        blog.setUpDateTime(upDateTime);
        blog.setCreateTime(createTime);
        blog.setBlogStatus(blogStatus);
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        blog.setCustomer(customer);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blog", blog);
            return "/blog/blog/update_blog_input.jsp";
        }
      //-------------------2.---------------------------------//
        // 修改資料
        blogService.updateBlog(blog);
        req.setAttribute("blog", blogService.getBlogByBlogId(blogId));

        return "/blog/blog/listOneBlog.jsp";
    }
//3.新增
    private String insert(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
      //-------------------1.---------------------------------//
        String title =String.valueOf(req.getParameter("title"));
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
        Timestamp blogDate =Timestamp.valueOf(req.getParameter("blog_date"));
        String content =String.valueOf(req.getParameter("content"));
        String blogStatus =String.valueOf(req.getParameter("blog_status"));
        
       Timestamp upDateTime =null;
       try {
    	   upDateTime = java.sql.Timestamp.valueOf(req.getParameter("upDateTime").trim());
	} catch (Exception e) {
		upDateTime =new java.sql.Timestamp(System.currentTimeMillis());
		errorMsgs.add("請輸入更新時間");
	}
       
       Timestamp createTime =null;
       try {
    	   createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
	} catch (Exception e) {
		createTime =new java.sql.Timestamp(System.currentTimeMillis());
		errorMsgs.add("請輸入發布時間");
	}
       

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        Blog blog = new Blog();
        blog.setTitle(title);	
        blog.setBlogDate(blogDate);
        blog.setContent(content);
        blog.setUpDateTime(upDateTime);
        blog.setCreateTime(createTime);
        blog.setBlogStatus(blogStatus);
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        blog.setCustomer(customer);
      //-------------------2.---------------------------------//
        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blog", blog);
            return "/blog/blog/addBlog.jsp";
        }
      //-------------------3.---------------------------------//
        // 新增資料
        blogService.addBlog(blog);

        return "/blog/blog/listAllBlog.jsp";
    }
}
