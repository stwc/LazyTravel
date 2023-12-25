package com.lazytravel.foodscape.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.foodscape.service.FoodScapeImgService;
import com.lazytravel.foodscape.service.FoodScapeImgServiceImpl;

@WebServlet("/blog/blog/BlogImgReader")
public class FoodScapeImgReader extends HttpServlet{

	
	private Integer imgId;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/*");
        ServletOutputStream out = res.getOutputStream();

        try {
            Integer foodScapeId = Integer.valueOf(req.getParameter("foodScapeId"));
            FoodScapeImgService foodscapeService = new FoodScapeImgServiceImpl();
            out.write(foodscapeService.getFoodScapeImgByImgId(imgId,foodScapeId).getImg());
        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/foodscape/image/101.jpg");
            byte[] buf = in.readAllBytes();
            out.write(buf);
            in.close();
        } finally {
            out.close();
        }
    }

}