package com.lazytravel.foodscape.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.foodscape.entity.FoodScape;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 獲取用戶提交的縣市名稱
        String city = request.getParameter("city");

        // 在這裡執行數據庫搜索邏輯，根據城市名稱獲取相應的數據
        List<FoodScape> searchResults = performDatabaseSearch(city);

        // 將結果存儲在request中
        request.setAttribute("searchResults", searchResults);

        // 轉發到顯示結果的JSP頁面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/selectpage.jsp");
        dispatcher.forward(request, response);
    }

    private List<FoodScape> performDatabaseSearch(String city) {
		return null ;
        // 實現你的數據庫搜索邏輯，根據城市名稱獲取相應的數據
        // 這裡假設你有一個名為YourResultClass的類來表示搜索結果
        // 你需要根據你的實際情況進行修改
        // ...
    }
}