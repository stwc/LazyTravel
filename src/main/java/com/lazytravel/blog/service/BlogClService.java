package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogLike;

public interface BlogClService {

	void addCl(BlogCl blogCl);

	void removeCl(BlogCl blogCl);
	
	 BlogCl getByPK(Integer customerId);
	 
	 List<BlogCl> getAllBlogCls();
}
