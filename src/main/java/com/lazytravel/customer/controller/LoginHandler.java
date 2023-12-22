package com.lazytravel.customer.controller;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginHandler", value = "/customer/login.do")
public class LoginHandler extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String indexPath = req.getContextPath() + "/index.jsp";
        String loginPath = "/customer/login.jsp";
        String authPath = "/customer/register-auth.jsp";

        final String email = req.getParameter("email");
        final String passwd = req.getParameter("customer_passwd");
        Customer customer = customerService.login(email, passwd);
        if (customer != null) {
            if (customer.getCustomerStatus().equals("0")) {
                // 帳號被停權
                req.setAttribute("isBanned", true);
                // 重導回登入頁面
                res.setContentType("text/html; charset=UTF-8");
                RequestDispatcher dispatcher = req.getRequestDispatcher(loginPath);
                dispatcher.forward(req, res);
                return;
            } else if (customer.getCustomerStatus().equals("1")) {
                // 登入成功
                HttpSession session = req.getSession();
                session.setAttribute("customer", customer);

                // 確認session id
                System.out.println("session id: " + session.getId());

                // 重導回首頁
                res.sendRedirect(indexPath);
            } else if (customer.getCustomerStatus().equals("2")) {
                // 帳號尚未驗證，重導至驗證頁面
                res.setContentType("text/html; charset=UTF-8");
                RequestDispatcher dispatcher = req.getRequestDispatcher(authPath);
                dispatcher.forward(req, res);
            }
        } else {
            // 登入失敗
            req.setAttribute("loginFailed", true);

            // 重導回登入頁面
            res.setContentType("text/html; charset=UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher(loginPath);
            dispatcher.forward(req, res);
            return;
        }
    }
}