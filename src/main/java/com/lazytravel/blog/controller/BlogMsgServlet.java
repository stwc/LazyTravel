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
import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.blog.service.BlogMsgService;
import com.lazytravel.blog.service.BlogMsgServiceImpl;
import com.lazytravel.blog.service.BlogServiceImpl;
import com.lazytravel.customer.entity.Customer;


@WebServlet(name = "BlogMsgServlet", value = "/blog/blogmsg/blogMsg.do")
public class BlogMsgServlet extends HttpServlet {
	private BlogMsgService blogMsgService;

	@Override
	public void init() throws ServletException {
		blogMsgService = new BlogMsgServiceImpl();
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
            case "delete":
            	 forwardPath = delete(req, res);
                 break;
            default:
                forwardPath = "/blog/blogmsg/blogMsg_select_page.jsp";
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
        String str = req.getParameter("blogMsgId");
        if (str == null || str.trim().isEmpty())
            errorMsgs.add("請輸入留言編號");
        if (!errorMsgs.isEmpty())
            return "/blog/blogmsg/blogMsg_select_page.jsp";
      //------------------1.-------------------------//
        Integer blogMsgId = null;
        try {
        	blogMsgId = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("留言ID格式不正確");
        }
        if (!errorMsgs.isEmpty())
            return "/blog/blogmsg/blogMsg_select_page.jsp";
      //------------------2.-------------------------//
        blogMsgService =new BlogMsgServiceImpl();
        BlogMsg blogMsg = blogMsgService.getBlogMsgByBlogMsgId(blogMsgId);
        if (blogMsg == null)
            errorMsgs.add("查無資料");
        if (!errorMsgs.isEmpty())
            return "/blog/blogmsg/blogMsg_select_page.jsp";
      //------------------3.-------------------------//
        req.setAttribute("blogMsg", blogMsg);
        return "/blog/blogmsg/listOneBlogMsg.jsp";
    }
 // 2.修改
    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
    	Integer blogMsgId = Integer.valueOf(req.getParameter("blog_msg_id"));
        BlogMsg blogMsg = blogMsgService.getBlogMsgByBlogMsgId(blogMsgId);

        req.setAttribute("blogMsg", blogMsg);
        return "/blog/blogmsg/update_blogMsg_input.jsp";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
      //-------------------1.---------------------------------//
        Integer blogMsgId =Integer.parseInt(req.getParameter("blog_msg_id"));
        String content = String.valueOf(req.getParameter("content"));
        Integer blogId =Integer.valueOf(req.getParameter("blog_id"));
        Integer customerId =Integer.valueOf(req.getParameter("customer_id"));
        
        Timestamp createTime =null;
        try {
     	   createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
 	} catch (Exception e) {
 		createTime =new java.sql.Timestamp(System.currentTimeMillis());
 		errorMsgs.add("請輸入發布時間");
 	}

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        BlogMsg blogMsg = new BlogMsg();
        blogMsg.setBlogMsgId(blogMsgId);
        blogMsg.setContent(content);
        blogMsg.setCreateTime(createTime);
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        blogMsg.setCustomer(customer);
        
        Blog blog =new Blog();
        blog.setBlogId(blogId);
        blogMsg.setBlog(blog);
        
        

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blogMsg", blogMsg);
            return "/blog/blogmsg/update_blogMsg_input.jsp";
        }
        //-------------------2.---------------------------------//
        // 修改資料
        blogMsgService.updateBlogMsg(blogMsg);
        req.setAttribute("blogMsg", blogMsgService.getBlogMsgByBlogMsgId(blogMsgId));

        return "/blog/blogmsg/listOneBlogMsg.jsp";
    }

    private String insert(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        Integer blogMsgId =Integer.valueOf(req.getParameter("blog_msg_id"));
        String content = String.valueOf(req.getParameter("content"));
        Integer blogId =Integer.valueOf(req.getParameter("blog_id"));
        Integer customerId =Integer.valueOf(req.getParameter("customer_id"));
        
        Timestamp createTime =null;
        try {
     	   createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
 	} catch (Exception e) {
 		createTime =new java.sql.Timestamp(System.currentTimeMillis());
 		errorMsgs.add("請輸入發布時間");
 	}

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        BlogMsg blogMsg = new BlogMsg();
        blogMsg.setBlogMsgId(blogMsgId);
        blogMsg.setContent(content);
        blogMsg.setCreateTime(createTime);
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        blogMsg.setCustomer(customer);
        
        Blog blog =new Blog();
        blog.setBlogId(blogId);
        blogMsg.setBlog(blog);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blogMsg", blogMsg);
            return "/blog/blogmsg/addBlogMsg.jsp";
        }

        // 新增資料
        blogMsgService.addBlogMsg(blogMsg);

        return "/blog/blogmsg/listAllBlogMsg.jsp";
    }
    private String delete(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        Integer blogMsgId =Integer.valueOf(req.getParameter("blog_msg_id"));
        BlogMsgService blogMsgSvc =new BlogMsgServiceImpl();
        blogMsgSvc.deleteBlogMsg(blogMsgId);
        
        return "/blog/blogmsg/listAllBlogMsg.jsp";
        		
    }
}
