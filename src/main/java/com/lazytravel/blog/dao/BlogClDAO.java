package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogImg;

public interface BlogClDAO {

	int insert(BlogCl entity); //新增收藏文章

	int delete(Integer blogClId); //取消收藏文章
	
	BlogCl getByPK(Integer cutomerId); //用會員ID查詢收藏文章
	
}
