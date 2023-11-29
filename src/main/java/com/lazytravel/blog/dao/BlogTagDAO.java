package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.blog.entity.BlogTag;

public interface BlogTagDAO {

	void insert(BlogTag entity);
	
	void update(BlogTag entity);
	
	void delete(Integer blogId,Integer tagId);
	
	BlogTag getByPK(Integer blogId,Integer tagId);
	
	List<BlogTag>getAll();
	
	List<BlogTag> getAll(int currentPage);

	long getTotal();
}
