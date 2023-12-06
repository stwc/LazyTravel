package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogImg;

public interface BlogImgDAO {
	
	int insert(BlogImg entity); //新增內文圖片
	
	int update(BlogImg entity); //修改內文圖片
	
	int delete(Integer blogImgId); //刪除內文圖片
	
	BlogImg getByPK(Integer blogImgId);  //查詢單筆筆
	
	List<BlogImg>getAll(); //查詢全部
	
}
