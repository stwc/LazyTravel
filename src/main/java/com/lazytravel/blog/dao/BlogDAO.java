package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;

public interface BlogDAO {

	int insert(Blog entity); //新增文章
	
	int update(Blog entity); //修改文章
	
	Blog getByPK(Integer blogId); //查詢文章
	
	List<Blog>getAll(); //找全部
	
}
