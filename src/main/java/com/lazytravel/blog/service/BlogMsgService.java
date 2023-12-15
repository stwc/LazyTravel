package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogMsg;

public interface BlogMsgService {
	BlogMsg  addBlogMsg(BlogMsg blogMsg);
	
	Integer updateBlogMsg(BlogMsg blogMsg);
	
	Integer deleteBlogMsg(Integer blogMsgId);
	
	BlogMsg getBlogMsgByBlogMsgId(Integer blogMsgId);
	
	List<BlogMsg> getAllBlogMsgs();
}
