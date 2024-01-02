package com.lazytravel.blog.service;

import java.util.List;

import javax.swing.undo.AbstractUndoableEdit;

import com.lazytravel.blog.dao.BlogClDAO;
import com.lazytravel.blog.dao.BlogClDAOImpl;
import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.customer.entity.Customer;

public class BlogClServiceImpl implements BlogClService {

    private BlogClDAO dao;
    
    public BlogClServiceImpl() {
    	dao = new BlogClDAOImpl();
    }

	@Override
	public BlogCl addBlogCl(BlogCl blogCl) {
		Integer id =dao.add(blogCl);
		blogCl =dao.getByPK(id);
		return blogCl;
	}

	@Override
	public Integer updateBlogCl(BlogCl blogCl) {
		return dao.update(blogCl);
	}

	@Override
	public BlogCl getBlogClByBlogClId(Integer blogClId) {
		return dao.getByPK(blogClId);
	}

	@Override
	public List<BlogCl> getAllBlogCls() {
		return dao.getAll();
	}

	@Override
	public String isBlogCl(Integer customerId,Integer blogId) {
		return dao.isBlogCl(customerId,blogId);
	}

	@Override
	public String addFavoriteCl(Integer customerId, Integer blogId) {
	return	dao.addFavoriteCl(customerId, blogId);
		
	}

	@Override
	public String unFavoriteCl(Integer customerId, Integer blogId) {
	return	dao.unFavoriteCl(customerId, blogId);
		
	}

	@Override
	public String updateFavoriteCl(Integer customerId, Integer blogId) {
	return	dao.updateFavoriteCl(customerId, blogId);
		
	}
	public List<BlogCl> getBlogClByCustomerId(Integer customerId){
		return dao.getBlogClByCustomerId(customerId);
	}

	public String blogClStatus(Blog blog,Customer customer) {
		return dao.blogClStatus(blog,customer);
	}
}
