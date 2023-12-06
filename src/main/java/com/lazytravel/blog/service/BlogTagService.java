package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogTag;

public interface BlogTagService {
	
void addBlogTag(BlogTag tagId) ;
	
    void updateBlogTag(BlogTag tagId);

    void deleteBlogTag(Integer tagId);

    BlogTag getBlogTagById(Integer blogId);

    List<BlogTag> getAllBlogs();

}
