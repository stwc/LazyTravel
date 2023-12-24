package com.lazytravel.admin.controller;

import com.google.gson.Gson;
import com.lazytravel.admin.service.FuncService;
import com.lazytravel.admin.service.FuncServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FuncServlet", value = "/admin/func.do")
public class FuncServlet extends HttpServlet {
    private FuncService funcService;

    @Override
    public void init() throws ServletException {
        funcService = new FuncServiceImpl();
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
            case "getall":
                String jsonStr = new Gson().toJson(funcService.getAll());
                res.setContentType("application/json; charset=UTF-8");
                res.getWriter().write(jsonStr);
                return;
            default:
                forwardPath = "/admin/index.jsp";
        }


        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }
}