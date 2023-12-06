package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogImg;

public interface BlogImgDAO {
	
	void insert(BlogImg entity);
	
	void update(BlogImg entity);
	
	void deledte(Integer blogImgId);
	
	BlogImg getByPK(Integer blogImgId);
	
	List<BlogImg>getAll();
	
	List<BlogImg>getAll(int currentPage);
	
	long getTotal();
}
