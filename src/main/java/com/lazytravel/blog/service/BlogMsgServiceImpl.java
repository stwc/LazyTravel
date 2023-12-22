package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogMsgDAO;
import com.lazytravel.blog.dao.BlogMsgDAOImpl;
import com.lazytravel.blog.entity.BlogMsg;

public class BlogMsgServiceImpl implements BlogMsgService {
	
	private BlogMsgDAO dao;
	
	public BlogMsgServiceImpl() {
		dao = new BlogMsgDAOImpl();
	}
	@Override
	public BlogMsg addBlogMsg(BlogMsg blogMsg) {
		Integer id =dao.add(blogMsg);
		blogMsg=dao.getByPK(id);
		return blogMsg;
	}

	@Override
	public Integer updateBlogMsg(BlogMsg blogMsg) {
		return dao.update(blogMsg);
	}

	@Override
	public Integer deleteBlogMsg(Integer blogMsgId) {
		return dao.delete(blogMsgId);
	}

	@Override
	public BlogMsg getBlogMsgByBlogMsgId(Integer blogMsgId) {
		return dao.getByPK(blogMsgId);
	}

	@Override
	public List<BlogMsg> getAllBlogMsgs() {
		return dao.gatAll();
	}
	public List<BlogMsg> getBlogMsgsByBlogId(Integer blogId){
		return dao.getBlogMsgsByBlogId(blogId);
	}
}
