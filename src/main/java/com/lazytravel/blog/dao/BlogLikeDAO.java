package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogLike;

public interface BlogLikeDAO {

	void insert(BlogLike entity);  //新增文章按讚

	void delete(Integer blogLikeId);  //刪除文章按讚

	BlogLike getByPK(Integer cusomterId); //查詢單筆

	List<BlogLike> getAll(); //查詢全部

}
