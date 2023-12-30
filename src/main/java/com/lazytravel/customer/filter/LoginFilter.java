package com.lazytravel.customer.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig config) {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object customer = session.getAttribute("customer");
        if (customer == null) {
            String location = req.getServletPath() + ((req.getPathInfo() == null) ? "" : req.getPathInfo()) +
                    ((req.getQueryString() == null) ? "" : "?" + req.getQueryString());
//            if (location.contains(".do"))
//                location = req.getServletPath();
//            String location = req.getRequestURI();
            System.out.println("Location before login: " + location);
            session.setAttribute("location", location);
//            res.sendRedirect(req.getContextPath() + "/customer/login.jsp");

            res.setContentType("text/html; charset=UTF-8");
            req.getRequestDispatcher("/customer/login.jsp").forward(req, res);

        } else {
            chain.doFilter(request, response);
        }
    }
}