package com.lazytravel.blog.service;

import com.lazytravel.blog.dao.BlogImgDAO;
import com.lazytravel.blog.dao.BlogImgDAOImpl;
import com.lazytravel.blog.entity.BlogImg;

import java.util.List;

public class BlogImgServiceImpl implements BlogImgService {

    private BlogImgDAO dao;

    // 透過建構子注入 BlogImgDAO 實例
    public BlogImgServiceImpl() {
        dao = new BlogImgDAOImpl();
    }

	@Override
	public BlogImg addBlogImg(BlogImg blogImg) {
		Integer id =dao.add(blogImg);
		blogImg=dao.getByPK(id);
		return blogImg;
	}

	@Override
	public Integer updateBlogImg(BlogImg blogImg) {
		return dao.update(blogImg);
	}

	@Override
	public Integer deleteBlogImg(Integer blogImgId) {
		return dao.delete(blogImgId);
	}

	@Override
	public BlogImg getBlogImgByBlogImgId(Integer blogImgId) {
		return dao.getByPK(blogImgId);
	}

	@Override
	public List<BlogImg> getAllBlogImgs() {
		return dao.getAll();
	}

    
}
