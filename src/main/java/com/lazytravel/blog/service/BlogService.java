package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.Blog;

public interface BlogService {

    Blog  addBlog(Blog blog) ;

    Integer updateBlog(Blog blog) ;

    Blog getBlogByBlogId(Integer blogId) ;

     List<Blog> getAllBlogs();
     
     Blog getBlogWithMsgs(Integer blogId);
     
     List<Blog> searchBlogsByKeyword(String keyword);
     
     void updateView(Integer blogId);
     
      List<Blog> getBlogByCustomerId(Integer customerId);
    
      
      List<Blog> getBlogClByCustomerId(Integer customerId);
}
