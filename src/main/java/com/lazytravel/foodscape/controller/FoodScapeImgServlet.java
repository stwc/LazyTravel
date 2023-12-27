//package com.lazytravel.foodscape.controller;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//
//import com.lazytravel.foodscape.service.FoodScapeService;
//
//@WebServlet(name = "FoodScapeImgServlet", value = "/foodscape/foodscapeimg.do")
//public class FoodScapeImgServlet extends HttpServlet {
//	private FoodScapeImgService foodscapeService;
//	
//public class FoodScapeImgServlet {
// byte[] blogImg = null;
//        try {
//            InputStream in = req.getPart("blogImg").getInputStream();
//            if (in.available() != 0) {
//             blogImg = new byte[in.available()];
//                in.read(blogImg);
//                in.close();
//            } else {
//                errorMsgs.add("文章圖片: 請上傳照片");
//            }
//        } catch (IOException | ServletException e) {
//            errorMsgs.add("圖片上傳失敗: " + e.getMessage());
//        }
//}
