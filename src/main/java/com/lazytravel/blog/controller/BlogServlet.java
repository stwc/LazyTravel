package com.lazytravel.blog.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.json.JSONException;
import org.json.JSONObject;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.blog.service.BlogClService;
import com.lazytravel.blog.service.BlogClServiceImpl;
import com.lazytravel.blog.service.BlogMsgService;
import com.lazytravel.blog.service.BlogMsgServiceImpl;
import com.lazytravel.blog.service.BlogService;
import com.lazytravel.blog.service.BlogServiceImpl;
import com.lazytravel.customer.entity.Customer;

@WebServlet(value = "/blog/blog/blog.do")
@MultipartConfig
public class BlogServlet extends HttpServlet {
	private BlogService blogService;
	private BlogClService blogClService;

	@Override
	public void init() throws ServletException {
		blogService = new BlogServiceImpl();
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
		
//		System.out.println(action);
		JSONObject json = null;
		
		if(action == null) {
		  	 BufferedReader reader = req.getReader();
	         StringBuilder requestData = new StringBuilder();
	         String line;
	         while ((line = reader.readLine()) != null) {
	             requestData.append(line);
	         }
	         
	         try {
	             json = new JSONObject(requestData.toString());
	              action  = json.getString("action");
//	              System.out.println(action);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
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
//            case "toggleFavorite":
//                forwardPath = toggleFavorite(req, res);
//                break;
            case "updownStatus":
                forwardPath =updownStatus(req,res);
                break;
            case "toggleFavorite":
                forwardPath = toggleFavorite(json, res);
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
        Integer viewSum=Integer.valueOf(req.getParameter("viewSum"));
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
        blog.setBlogId(blogId);
        blog.setTitle(title);
        blog.setBlogDate(blogTimestamp);
        blog.setContent(content);
        blog.setUpDateTime(upDateTime);
        blog.setCreateTime(createTime);
        blog.setBlogStatus(blogStatus);
        blog.setImg(blogImg);
        blog.setViewSum(viewSum);
        
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

        return "/blog/blog/myblog.jsp";
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
        Integer viewSum=Integer.valueOf(req.getParameter("viewSum"));
        
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
        blog.setViewSum(viewSum);
        
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

        return "/blog/blog/myblog.jsp";
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

        request.setAttribute("searchResults", searchResults);

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
//        dispatcher.forward(request, response);
        
        return "/blog/blog/search.jsp";
    }
    
    
    private String toggleFavorite(JSONObject json, HttpServletResponse res) throws IOException {
//        Integer blogId =Integer.valueOf(req.getParameter("blogId"));
//        Integer customerId =Integer.valueOf(req.getParameter("customerId"));
//        
//        var isLoggedIn = true;
//        if (isLoggedIn) {
//            // 使用 BlogClDAO 將 blogId 收藏到後端
//            if (blogClService.isBlogCl(customerId,blogId)) {
//                // 如果已經收藏，執行取消收藏的邏輯
//               blogClService.unFavoriteCl(customerId,blogId);
//                req.setAttribute("message", "取消收藏成功！");
//            } else {
//                // 如果未收藏，執行收藏的邏輯
//                blogClService.onFavoriteCl(customerId,blogId);
//                req.setAttribute("message", "收藏成功！");
//            }
//        } else {
//            // 用戶未登入，可以導向登入頁面或顯示提示
//            req.setAttribute("error", "請先登入後再收藏。");
//        }
//
//        // 重新導向或返回結果
//        return "1"; // 將這裡的 "your-jsp-page.jsp" 替換為你想要顯示的 JSP 頁面
//    	HttpSession session = req.getSession();
//    	Customer customer = (Customer) session.getAttribute("customer");
    	
//    	 BufferedReader reader = req.getReader();
//         StringBuilder requestData = new StringBuilder();
//         String line;
//         while ((line = reader.readLine()) != null) {
//        	 System.out.println(line);
//             requestData.append(line);
//         }
         try {
//             json = new JSONObject(requestData.toString());
        	 
             Integer blogId = json.getInt("blogId");
             Integer customerId = json.getInt("customerId");
             System.out.println("blogID="+blogId);
             System.out.println("customerid="+customerId);
             
             // 在實際應用中，這裡可以根據需要進行收藏或取消收藏的邏輯
             String blogClStatus = blogClService.isBlogCl(customerId, blogId);
             System.out.println("IF外面的"+blogClStatus);
             
             if (! blogClStatus.equals("novalue")) {
            	 blogClService.updateFavoriteCl(customerId, blogId);
            	    // 如果有收藏資料
            	 System.out.println("IF裡面的="+blogClStatus);
            	    if ("0".equals(blogClStatus) ) {
            	        // 如果收藏狀態為 0，將收藏狀態修改為 1
            	        System.out.println("收藏狀態為 0，修改為 1");
            	        blogClService.updateFavoriteCl(customerId, blogId);
            	    } else {
            	        // 其他邏輯，例如取消收藏
            	        System.out.println("執行其他邏輯，例如取消收藏");
            	        blogClService.unFavoriteCl(customerId, blogId);
            	    }
            	} else {
            	    // 如果沒有收藏資料，新增收藏資料，並將收藏狀態設置為 1
            	    System.out.println("沒有收藏資料，新增收藏資料，收藏狀態設置為 1");
            	    blogClService.addFavoriteCl(customerId, blogId);
            	}

             // 回傳 JSON 格式的成功訊息
             res.setContentType("application/json");
             res.setCharacterEncoding("UTF-8");
             PrintWriter out = res.getWriter();
             out.write("{\"result\": \"success\"}");
             out.flush();
         } catch (JSONException e) {
             e.printStackTrace();
             // 回傳 JSON 格式的錯誤訊息
             res.setContentType("application/json");
             res.setCharacterEncoding("UTF-8");
             PrintWriter out = res.getWriter();
             out.write("{\"error\": \"處理 JSON 資料時發生錯誤。\"}");
             out.flush();
        	 e.printStackTrace();
         }
         res.getWriter().close();
		return "0";
         
     }
    
    
    
    
    
    
    
    
    private String updownStatus(HttpServletRequest req, HttpServletResponse res) {
        Integer blogId = Integer.valueOf(req.getParameter("blogId"));
        BlogService blogService = new BlogServiceImpl();
        Blog blog = blogService.getBlogByBlogId(blogId);
        
        String currentStatus = blog.getBlogStatus();
        String newStatus = (currentStatus.equals("0")) ? "1" : "0";
        blog.setBlogStatus(newStatus);
        blogService.updateBlog(blog);
        
        req.setAttribute("blog", blog);

        return "/blog/html/backbloglist.jsp";  
}

}