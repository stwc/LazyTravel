package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;

public interface BlogDAO {

	int insert(Blog entity);
	
	int update(Blog entity);
	
	Blog getByPK(Integer blogId);
	
	List<Blog>getAll();
	
	List<Blog>getAll(int currentPage);
	
	long getTotal();
}
