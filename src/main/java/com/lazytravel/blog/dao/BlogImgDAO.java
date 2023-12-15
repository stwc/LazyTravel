package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogImg;

public interface BlogImgDAO {
	
	public Integer add(BlogImg blogImg); //新增內文圖片
	
	public Integer update(BlogImg blogImg); //修改內文圖片
	
	public Integer delete(Integer blogImgId); //刪除內文圖片
	
	public BlogImg getByPK(Integer blogImgId);  //查詢單筆筆
	
	public List<BlogImg>getAll(); //查詢全部
	
}
