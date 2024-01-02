package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogLike;

public interface BlogLikeDAO {

	public Integer  add(BlogLike blogLike);  //新增文章按讚

	public Integer update(BlogLike blogLike);  //刪除文章按讚

	public BlogLike getByPK(Integer blogLikeId); //查詢單筆

 	public	List<BlogLike> getAll(); //查詢全部
 	
public String isBlogLike(Integer customerId,Integer blogId) ;
	
	String addLike(Integer customerId,Integer blogId);
	
	String unLike(Integer customerId,Integer blogId);
	
	String onLike(Integer customerId,Integer blogId);
}
