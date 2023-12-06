package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogLike;

public interface BlogLikeService {

	void addBlogLike(BlogLike blogLike) ;
	
//    void updateBlogLike(BlogLike blogLike);

    void deleteBlogLike(Integer blogLikeId);

    BlogLike getByPK(Integer blogLikeId);

    List<BlogLike> getAllBlogLikes();

}
