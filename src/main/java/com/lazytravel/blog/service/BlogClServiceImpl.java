package com.lazytravel.blog.service;

import com.lazytravel.blog.dao.BlogClDAO;
import com.lazytravel.blog.entity.BlogCl;

public class BlogClServiceImpl implements BlogClService {

    private BlogClDAO dao;

    // 在構造函數中初始化 blogClDAO

    public BlogClServiceImpl(BlogClDAO blogClDAO) {
        this.dao = blogClDAO;
    }

    @Override
    public void addLike(BlogCl blogCl) {
        // 添加相應的邏輯，例如檢查是否已經點過讚
        dao.insert(blogCl);
    }

    @Override
    public void removeLike(BlogCl blogCl) {
        // 添加相應的邏輯，例如確保存在再刪除
        dao.delete(blogCl.getBlogClId());
    }
}
