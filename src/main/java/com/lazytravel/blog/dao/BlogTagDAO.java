package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.blog.entity.BlogTag;

public interface BlogTagDAO {

	void insert(BlogTag entity); //新增標籤
	
	void update(BlogTag entity); //修改標籤
	
	void delete(Integer blogId,Integer tagId); //刪除標籤
	
	BlogTag getByPK(Integer blogId,Integer tagId); //查單筆
	
	List<BlogTag>getAll(); //找全部
}
