package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogLikeDAO;
import com.lazytravel.blog.dao.BlogLikeDAOImpl;
import com.lazytravel.blog.entity.BlogLike;

public class BlogLikeServiceImpl implements BlogLikeService{
	
	private BlogLikeDAO dao ;
	
	public BlogLikeServiceImpl() {
		dao = new BlogLikeDAOImpl();
	}

	@Override
	public BlogLike addBlogLike(BlogLike blogLike) {
		Integer id = dao.add(blogLike);
		blogLike =dao.getByPK(id);
		return blogLike;
	}

	@Override
	public Integer updateBlogLike(BlogLike blogLike) {
		return dao.update(blogLike);
	}

	@Override
	public BlogLike getBlogLikeByBlogLikeId(Integer blogLikeId) {
		return dao.getByPK(blogLikeId);
	}

	@Override
	public List<BlogLike> getAllBlogLikes() {
		return dao.getAll();
	}

	@Override
	public String isBlogLike(Integer customerId, Integer blogId) {
		return dao.isBlogLike(customerId, blogId);
	}

	@Override
	public String addLike(Integer customerId, Integer blogId) {
		return dao.addLike(customerId, blogId);
	}

	@Override
	public String unLike(Integer customerId, Integer blogId) {
		return dao.unLike(customerId, blogId);
	}

	@Override
	public String onLike(Integer customerId, Integer blogId) {
		return dao.onLike(customerId, blogId);
	}

	
}
