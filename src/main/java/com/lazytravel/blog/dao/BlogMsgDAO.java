package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.blog.entity.BlogMsg;

public interface BlogMsgDAO {

	void insert(BlogMsg entity);  //新增留言
	
	void update(BlogMsg entity);  //修改留言
	
	void delete(Integer blogMsgId); //刪除留言
	
	BlogMsg getByPK(Integer customerId);  //查詢單筆
	
	List<BlogMsg>gatALL(); //找全部
	  
}
