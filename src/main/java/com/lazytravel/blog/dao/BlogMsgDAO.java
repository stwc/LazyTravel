package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.blog.entity.BlogMsg;

public interface BlogMsgDAO {

	void insert(BlogMsg entity);
	
	void update(BlogMsg entity);
	
	void delete(Integer blogMsgId);
	
	BlogMsg getByPK(Integer blogMsgId);
	
	List<BlogMsg>gatALL();
	
	List<BlogMsg> getAll(int currentPage);

	long getTotal();
}
