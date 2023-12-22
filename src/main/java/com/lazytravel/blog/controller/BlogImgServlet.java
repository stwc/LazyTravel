package com.lazytravel.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.blog.entity.BlogImg;
import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.service.BlogImgService;
import com.lazytravel.blog.service.BlogImgServiceImpl;
import com.lazytravel.customer.entity.Customer;

@WebServlet(value = "/blog/blogimg/blogImg.do")
@MultipartConfig
public class BlogImgServlet extends HttpServlet{
	private BlogImgService blogImgService;
	
	@Override
	public void init() throws ServletException {
		blogImgService = new BlogImgServiceImpl();
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
                forwardPath = "/blog/blogimg/blogImg_select_page.jsp";
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
        String str = req.getParameter("blogImgId");
        if (str == null || str.trim().isEmpty())
            errorMsgs.add("請輸入留言編號");
        if (!errorMsgs.isEmpty())
            return "/blog/blogimg/blogImg_select_page.jsp";
      //------------------1.-------------------------//
        Integer blogImgId = null;
        try {
        	blogImgId = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("留言ID格式不正確");
        }
        if (!errorMsgs.isEmpty())
            return "/blog/blogimg/blogImg_select_page.jsp";
      //------------------2.-------------------------//
        blogImgService =new BlogImgServiceImpl();
        BlogImg blogImg = blogImgService.getBlogImgByBlogImgId(blogImgId);
        if (blogImg == null)
            errorMsgs.add("查無資料");
        if (!errorMsgs.isEmpty())
            return "/blog/blogimg/blogImg_select_page.jsp";
      //------------------3.-------------------------//
        req.setAttribute("blogImg", blogImg);
        return "/blog/blogimg/listOneBlogImg.jsp";
    }
 // 2.修改
    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
    	Integer blogImgId = Integer.valueOf(req.getParameter("blogImgId"));
        BlogImg blogImg = blogImgService.getBlogImgByBlogImgId(blogImgId);

        req.setAttribute("blogImg", blogImg);
        return "/blog/blogimg/update_blogImg_input.jsp";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        Integer blogImgId = Integer.parseInt(req.getParameter("blogImgId"));
        Integer blogId = Integer.valueOf(req.getParameter("blog_id"));

        BlogImg blogImg = new BlogImg();

        byte[] upFiles = null;

        try {
            InputStream in = req.getPart("upFiles").getInputStream();
            if (in.available() != 0) {
                upFiles = new byte[in.available()];
                in.read(upFiles);
                in.close();
            } else {
                errorMsgs.add("文章圖片: 請上傳照片");
            }
        } catch (IOException | ServletException e) {
            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
        }

        Timestamp createTime = null;
        try {
            createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
        } catch (Exception e) {
            createTime = new java.sql.Timestamp(System.currentTimeMillis());
            errorMsgs.add("請輸入發布時間");
        }

        blogImg.setBlogImgId(blogImgId);
        blogImg.setCreateTime(createTime);

        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blogImg.setBlog(blog);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blogImg", blogImg);
            return "/blog/blogimg/update_blogImg_input.jsp";
        }

        blogImgService.updateBlogImg(blogImg);
        req.setAttribute("blogImg", blogImgService.getBlogImgByBlogImgId(blogImgId));
        return "/blog/blogimg/listOneBlogImg.jsp";
    }
    private String insert(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
    	List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        Integer blogId = Integer.valueOf(req.getParameter("blog_id"));
        
        byte[] upFiles = null;

        try {
            InputStream in = req.getPart("upFiles").getInputStream();
            if (in.available() != 0) {
                upFiles = new byte[in.available()];
                in.read(upFiles);
                in.close();
            } else {
                errorMsgs.add("文章圖片: 請上傳照片");
            }
        } catch (IOException | ServletException e) {
            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
        }

        Timestamp createTime = null;
        try {
            createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
        } catch (Exception e) {
            createTime = new java.sql.Timestamp(System.currentTimeMillis());
            errorMsgs.add("請輸入發布時間");
        }

        BlogImg blogImg = new BlogImg();
        blogImg.setImg(upFiles);
        blogImg.setCreateTime(createTime);

        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blogImg.setBlog(blog);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blogImg", blogImg);
            return "/blog/blogimg/addBlogImg.jsp";
        }

        blogImgService.addBlogImg(blogImg);

        return "/blog/blogimg/listAllBlogImg.jsp";
    }
    private String delete(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
        Integer blogImgId =Integer.valueOf(req.getParameter("blogImgId"));
        BlogImgService blogImgSvc =new BlogImgServiceImpl();
        blogImgSvc.deleteBlogImg(blogImgId);
        
        return "/blog/blogimg/listAllBlogImg.jsp";
        		
    }
}