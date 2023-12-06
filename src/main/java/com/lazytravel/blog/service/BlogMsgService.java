package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogMsg;

public interface BlogMsgService {

	void addBlogMsg(BlogMsg blogmsg);

	void updateBlogMsg(BlogMsg blogmsg);

	void deleteBlogLike(Integer blogmsg);

	BlogMsg getByPK(Integer blogMsgId);

	List<BlogMsg> getAllBlogMsgs();

}
