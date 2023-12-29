package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogClDAO;
import com.lazytravel.blog.dao.BlogClDAOImpl;
import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;

public interface BlogClService {

	BlogCl addBlogCl(BlogCl blogCl);

	Integer  updateBlogCl(BlogCl blogCl) ;

	 BlogCl getBlogClByBlogClId(Integer blogClId);
	 
	 List<BlogCl>getAllBlogCls();
	 
	 String isBlogCl(Integer customerId,Integer blogId);
	 
	 void addFavoriteCl(Integer customerId,Integer blogId) ;
	 
	 void unFavoriteCl(Integer customerId,Integer blogId) ;
	 
	 void updateFavoriteCl(Integer customerId,Integer blogId);
		 
	 List<BlogCl> getBlogClByCustomerId(Integer customerId);
}
