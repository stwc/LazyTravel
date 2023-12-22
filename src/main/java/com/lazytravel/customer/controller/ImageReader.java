package com.lazytravel.customer.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;

@WebServlet("/customer/ImageReader")
public class ImageReader extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("image/jpeg");
        ServletOutputStream out = res.getOutputStream();

        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            CustomerService customerService = new CustomerServiceImpl();
            out.write(customerService.getOneCustomer(id).getAvatar());
        } catch (Exception e) {
            InputStream in = getServletContext().getResourceAsStream("/static/images/avatar-default.jpg");
            byte[] buf = in.readAllBytes();
            out.write(buf);
            in.close();
        } finally {
            out.close();
        }
    }

}
