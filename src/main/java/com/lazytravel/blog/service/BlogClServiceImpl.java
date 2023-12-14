package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogClDAO;
import com.lazytravel.blog.dao.BlogClDAOImpl;
import com.lazytravel.blog.entity.BlogCl;

public class BlogClServiceImpl implements BlogClService {

    private BlogClDAO dao;
    
    public BlogClServiceImpl() {
    	dao = new BlogClDAOImpl();
    }

	@Override
	public BlogCl addBlogCl(BlogCl blogCl) {
		Integer id =dao.add(blogCl);
		blogCl =dao.getByPK(id);
		return blogCl;
	}

	@Override
	public Integer updateBlogCl(BlogCl blogCl) {
		return dao.update(blogCl);
	}

	@Override
	public BlogCl getBlogClByBlogClId(Integer blogClId) {
		return dao.getByPK(blogClId);
	}

	@Override
	public List<BlogCl> getAllBlogCls() {
		return dao.getAll();
	}

}
