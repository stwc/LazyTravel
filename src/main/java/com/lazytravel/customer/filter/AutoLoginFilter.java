package com.lazytravel.customer.filter;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login.do", "/login.jsp"})
public class AutoLoginFilter implements Filter {

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
        System.out.println("session id: " + session.getId());

        // 去cookie找「AUTH_TOKEN」，判斷之前是否登入過且token尚未過期
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                System.out.println("cookie name: " + cookie.getName());
                if (cookie.getName().equals("AUTH_TOKEN")) {
                    String token = cookie.getValue();
//                    System.out.println("token:" + token);
                    Customer loginInfo = new CustomerServiceImpl().getAutoLoginInfo(token);
                    if (loginInfo != null) {
                        session.setAttribute("customer", loginInfo);
                        System.out.println("[會員] 自動登入成功！");
                    } else {
                        System.out.println("[會員] 沒有該會員的資料");
                    }
                    break;
                }
            }
        } else {
            System.out.println("cookie is null");
        }

        chain.doFilter(request, response);
    }
}