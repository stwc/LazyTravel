package com.lazytravel.blog.service;

import com.lazytravel.blog.dao.BlogImgDAO;
import com.lazytravel.blog.dao.BlogImgDAOImpl;
import com.lazytravel.blog.entity.BlogImg;

import java.util.List;

public interface BlogImgService {

	BlogImg addBlogImg(BlogImg blogImg) ;

	 Integer updateBlogImg(BlogImg blogImg);

	 Integer deleteBlogImg(Integer blogImgId);

	 BlogImg getBlogImgByBlogImgId(Integer blogImgId);

	 List<BlogImg> getAllBlogImgs();
}
