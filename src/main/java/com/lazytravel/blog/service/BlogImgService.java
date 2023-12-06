package com.lazytravel.blog.service;

import com.lazytravel.blog.entity.BlogImg;

import java.util.List;

public interface BlogImgService {

	void addBlogImg(BlogImg blogImg);

	void updateBlogImg(BlogImg blogImg);

	void deleteBlogImg(Integer blogImgId);

	BlogImg getBlogImgById(Integer blogImgId);

	List<BlogImg> getAllBlogImgs();

}
