package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;

public interface BlogDAO {

	public Integer add(Blog blog); //新增文章
	
	public Integer update(Blog blog); //修改文章
	
	public Blog getByPK(Integer blogId); //查詢文章
	
	public List<Blog>getAll(); //找全部
	
	public Blog getBlogWithMsgs(Integer blogId);
	
}
