package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogLike;

public interface BlogLikeService {

	void addBlogLike(BlogLike blogLike) ;
	
    // 更新圖片的業務邏輯
    void updateBlogLike(BlogLike blogLike);

    // 刪除圖片的業務邏輯
    void deleteBlogLike(Integer blogLikeId);

    // 獲取圖片的業務邏輯
    BlogLike getByPK(Integer blogLikeId);

    // 獲取所有部落格圖片的業務邏輯
    List<BlogLike> getAllBlogLikes();

}
