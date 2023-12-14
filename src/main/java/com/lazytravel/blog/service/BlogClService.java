package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogClDAO;
import com.lazytravel.blog.dao.BlogClDAOImpl;
import com.lazytravel.blog.entity.BlogCl;

public interface BlogClService {

	BlogCl addBlogCl(BlogCl blogCl);

	Integer  updateBlogCl(BlogCl blogCl) ;

	 BlogCl getBlogClByBlogClId(Integer blogClId);
	 
	 List<BlogCl>getAllBlogCls();
}
