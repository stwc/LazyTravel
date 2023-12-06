package com.lazytravel.blog.service;

import com.lazytravel.blog.dao.BlogImgDAO;
import com.lazytravel.blog.entity.BlogImg;

import java.util.List;

public class BlogImgServiceImpl implements BlogImgService {

    private BlogImgDAO dao;

    // 透過建構子注入 BlogImgDAO 實例
    public BlogImgServiceImpl(BlogImgDAO blogImgDAO) {
        this.dao = blogImgDAO;
    }

    @Override
    public void addBlogImg(BlogImg blogImg) {
        dao.insert(blogImg);
    }

    @Override
    public void updateBlogImg(BlogImg blogImg) {
        dao.update(blogImg);
    }

    @Override
    public void deleteBlogImg(Integer blogImgId) {
        dao.delete(blogImgId);
    }

    @Override
    public BlogImg getBlogImgById(Integer blogImgId) {
        return dao.getByPK(blogImgId);
    }

    @Override
    public List<BlogImg> getAllBlogImgs() {
        return dao.getAll();
    }

    @Override
    public List<BlogImg> getBlogImgsByPage(int currentPage) {
        return dao.getAll(currentPage);
    }

    @Override
    public long getTotalBlogImgs() {
        return dao.getTotal();
    }
}
