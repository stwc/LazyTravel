package com.lazytravel.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.blog.service.BlogMsgService;
import com.lazytravel.blog.service.BlogMsgServiceImpl;
import com.lazytravel.blog.service.BlogService;
import com.lazytravel.blog.service.BlogServiceImpl;
import com.lazytravel.customer.entity.Customer;

@WebServlet(value = "/blog/blog/blog.do")
@MultipartConfig
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
            case "getBlogMsgsByBlogId":
                forwardPath = getBlogMsgsByBlogId(req, res);
                break;
            case "search":
            forwardPath =handleSearch(req,res);
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
	        Integer blogId =Integer.valueOf(req.getParameter("blogId"));
	      //------------------2.-------------------------//
	        blogService = new BlogServiceImpl();
	        Blog blog = blogService.getBlogByBlogId(blogId);
	        
	        blogService.updateView(blogId);
	      //------------------3.-------------------------//
	        req.setAttribute("blog", blog);
	        return "/blog/blog/readblog.jsp";
	    }
// 2.修改
    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
        Blog blog = blogService.getBlogByBlogId(blogId);
        

        req.setAttribute("blog", blog);
        return "/blog/blog/updateblog.jsp";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);
        
//-------------------1.---------------------------------//
        Integer blogId =Integer.parseInt(req.getParameter("blogId"));
        String title =String.valueOf(req.getParameter("title"));
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
        String blogDateStr = req.getParameter("blog_date");
        String content =String.valueOf(req.getParameter("content"));
        Timestamp upDateTime =Timestamp.valueOf(req.getParameter("updateTime"));
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Timestamp blogDate =Timestamp.valueOf(req.getParameter("blog_date"))
        String blogStatus =String.valueOf(req.getParameter("blogStatus"));
        Timestamp blogTimestamp = null;
        
        try {
            // 從 request 中取得 blog_date 參數
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
            // 解析日期
            LocalDate localDate = LocalDate.parse(blogDateStr, inputFormatter);
            
            // 使用當前時間的 LocalTime
            LocalTime currentTime = LocalTime.now();

            // 將 LocalDate 和 LocalTime 組合成 LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.of(localDate, currentTime);
            
            // 將 LocalDateTime 轉換為 Timestamp
            blogTimestamp = Timestamp.valueOf(localDateTime);
            
            // 輸出到控制台查看
            System.out.println("Formatted Date: " + blogTimestamp);
            
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        
        Timestamp createTime =null;
        try {
     	   createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
 	} catch (Exception e) {
 		createTime =new java.sql.Timestamp(System.currentTimeMillis());
 		errorMsgs.add("請輸入發布時間");
 	}
        
        byte[] blogImg = null;
        try {
            InputStream in = req.getPart("blogImg").getInputStream();
            if (in.available() != 0) {
            	blogImg = new byte[in.available()];
                in.read(blogImg);
                in.close();
            } else {
                errorMsgs.add("文章圖片: 請上傳照片");
            }
        } catch (IOException | ServletException e) {
            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
        }
        

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setTitle(title);
        blog.setBlogDate(blogTimestamp);
        blog.setContent(content);
        blog.setUpDateTime(upDateTime);
        blog.setCreateTime(createTime);
        blog.setBlogStatus(blogStatus);
        blog.setImg(blogImg);
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        blog.setCustomer(customer);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blog", blog);
            return "/blog/blog/updateblog.jsp";
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
        String blogDateStr = req.getParameter("blog_date");
        String content =String.valueOf(req.getParameter("content"));
        Timestamp upDateTime =Timestamp.valueOf(req.getParameter("updateTime"));
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Timestamp blogDate =Timestamp.valueOf(req.getParameter("blog_date"))
        String blogStatus =String.valueOf(req.getParameter("blogStatus"));
        Timestamp blogTimestamp = null;
        
        try {
            // 從 request 中取得 blog_date 參數
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // 解析日期
            LocalDate localDate = LocalDate.parse(blogDateStr, inputFormatter);
            
            // 使用當前時間的 LocalTime
            LocalTime currentTime = LocalTime.now();

            // 將 LocalDate 和 LocalTime 組合成 LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.of(localDate, currentTime);
            
            // 將 LocalDateTime 轉換為 Timestamp
            blogTimestamp = Timestamp.valueOf(localDateTime);
            
            // 輸出到控制台查看
            System.out.println("Formatted Date: " + blogTimestamp);
            
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        
        Timestamp createTime =null;
        try {
     	   createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
 	} catch (Exception e) {
 		createTime =new java.sql.Timestamp(System.currentTimeMillis());
 		errorMsgs.add("請輸入發布時間");
 	}
        
        byte[] blogImg = null;
        try {
            InputStream in = req.getPart("blogImg").getInputStream();
            if (in.available() != 0) {
            	blogImg = new byte[in.available()];
                in.read(blogImg);
                in.close();
            } else {
                errorMsgs.add("文章圖片: 請上傳照片");
            }
        } catch (IOException | ServletException e) {
            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
        }
        

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBlogDate(blogTimestamp);
        blog.setContent(content);
        blog.setUpDateTime(upDateTime);
        blog.setCreateTime(createTime);
        blog.setBlogStatus(blogStatus);
        blog.setImg(blogImg);
        
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        blog.setCustomer(customer);
      //-------------------2.---------------------------------//
        if (!errorMsgs.isEmpty()) {
            req.setAttribute("blog", blog);
            return "/blog/blog/addblog2.jsp";
        }
      //-------------------3.---------------------------------//
        // 新增資料
        blogService.addBlog(blog);

        return "/blog/blog/blogfirst.jsp";
    }
    
    private String getBlogMsgsByBlogId(HttpServletRequest req, HttpServletResponse res) {
        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
        BlogMsgService blogMsgService = new BlogMsgServiceImpl();
        
        // 調用 BlogService 中的方法，根據 blogId 獲取相關的留言
        List<BlogMsg> blogMsgs = blogMsgService.getBlogMsgsByBlogId(blogId);

        // 將獲取的留言列表設置到 request 中，以便在 JSP 頁面中顯示
        req.setAttribute("blogMsgs", blogMsgs);

        return "/your-jsp-page.jsp";  // 將這裡的 "your-jsp-page.jsp" 替換為你想要顯示的 JSP 頁面
    }
    private String handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Blog> searchResults = blogService.searchBlogsByKeyword(keyword);

        // 将搜索结果存储到请求属性中
        request.setAttribute("searchResults", searchResults);

//        // 转发到展示搜索结果的页面
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
//        dispatcher.forward(request, response);
        
        return "/blog/blog/search.jsp";
    }
    
}

