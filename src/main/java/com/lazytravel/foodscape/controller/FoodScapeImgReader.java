package com.lazytravel.foodscape.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.foodscape.service.FoodScapeService;
import com.lazytravel.foodscape.service.FoodScapeServiceImpl;

@WebServlet("/FoodScapeImgReader")
public class FoodScapeImgReader extends HttpServlet{


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/*");
        ServletOutputStream out = res.getOutputStream();

        try {
            Integer foodScapeId = Integer.valueOf(req.getParameter("foodScapeId"));
            FoodScapeService foodscapeService = new FoodScapeServiceImpl();
            out.write(foodscapeService.getFoodScapeByFoodScapeId(foodScapeId).getImg());
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