package com.lazytravel.blog.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lazytravel.blog.dao.BlogDAO;
import com.lazytravel.blog.dao.BlogDAOImpl;
import com.lazytravel.blog.entity.Blog;
import com.lazytravel.util.HibernateUtil;

public class BlogServiceImpl implements BlogService {
	private  BlogDAO dao;
	
	public BlogServiceImpl() {
		dao = new BlogDAOImpl();
	}

   public Blog addBlog(Blog blog) {
    	Integer id =dao.add(blog);
    	blog =dao.getByPK(id);
    	return blog;
    }
    
    public Blog getBlogByBlogId(Integer blogId) {
    	return dao.getByPK(blogId);
    }
    
    public List<Blog> getAllBlogs(){
    	return dao.getAll();
    }

	@Override
	public Blog getBlogWithMsgs(Integer blogId) {
		return null;
	}

	@Override
	public Integer updateBlog(Blog blog) {
		return dao.update(blog);
	}

    
}
