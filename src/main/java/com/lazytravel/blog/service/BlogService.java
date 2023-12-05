package com.lazytravel.blog.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lazytravel.blog.entity.Blog;

public interface BlogService {

    int createBlog(Blog blog);
    
    int updateBlog(Blog blog);
    
    Blog getBlogById(Integer customerId);
    
    List<Blog> getAllBlogs();
    
}
