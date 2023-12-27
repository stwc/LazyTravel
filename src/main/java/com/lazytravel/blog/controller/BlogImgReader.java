package com.lazytravel.blog.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.blog.service.BlogService;
import com.lazytravel.blog.service.BlogServiceImpl;


@WebServlet("/blog/blog/BlogImgReader")
public class BlogImgReader extends HttpServlet{

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/*");
        ServletOutputStream out = res.getOutputStream();

        try {
            Integer blogId = Integer.valueOf(req.getParameter("blogId"));
            BlogService blogService = new BlogServiceImpl();
            out.write(blogService.getBlogByBlogId(blogId).getImg());
        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/static/blogimages/大王拉麵.jpg");
            byte[] buf = in.readAllBytes();
            out.write(buf);
            in.close();
        } finally {
            out.close();
        }
    }

}
	
