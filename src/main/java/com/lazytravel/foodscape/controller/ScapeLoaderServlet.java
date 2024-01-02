package com.lazytravel.foodscape.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/scapeLoader")
public class ScapeLoaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String IMAGE_FOLDER_PATH = "C:\\THA104_Workspace\\lazytravel\\src\\main\\webapp\\foodscape\\scape";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> imagePaths = getRandomImages();

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 将图像路径转换为JSON格式
        Gson gson = new Gson();
        String json = gson.toJson(imagePaths);
        out.println(json);
    }

    private List<String> getRandomImages() {
        File folder = new File(IMAGE_FOLDER_PATH);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
        List<String> imagePaths = new ArrayList<>();

        if (files != null && files.length > 0) {
            int numberOfImages = Math.min(3, files.length);
            List<File> shuffledFiles = Arrays.asList(files);
            Random random = new Random();
            shuffledFiles.sort((a, b) -> random.nextInt());

            for (int i = 0; i < numberOfImages; i++) {
                imagePaths.add(shuffledFiles.get(i).getName());
            }
        }

        return imagePaths;
    }
}
