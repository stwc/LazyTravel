package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogLikeDAO;
import com.lazytravel.blog.dao.BlogLikeDAOImpl;
import com.lazytravel.blog.entity.BlogLike;

public interface BlogLikeService {
	
	BlogLike addBlogLike(BlogLike blogLike);
	
     Integer updateBlogLike(BlogLike blogLike);

     BlogLike getBlogLikeByBlogLikeId(Integer blogLikeId);

     List<BlogLike> getAllBlogLikes();

}
