package com.lazytravel.blog.service;

import com.lazytravel.blog.entity.BlogImg;

import java.util.List;

public interface BlogImgService {

    // 新增圖片的業務邏輯
    void addBlogImg(BlogImg blogImg);

    // 更新圖片的業務邏輯
    void updateBlogImg(BlogImg blogImg);

    // 刪除圖片的業務邏輯
    void deleteBlogImg(Integer blogImgId);

    // 獲取圖片的業務邏輯
    BlogImg getBlogImgById(Integer blogImgId);

    // 獲取所有部落格圖片的業務邏輯
    List<BlogImg> getAllBlogImgs();

}
