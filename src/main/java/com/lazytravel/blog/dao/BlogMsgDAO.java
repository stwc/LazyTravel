package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.blog.entity.BlogMsg;

public interface BlogMsgDAO {

	public Integer add(BlogMsg blogMsg);  //新增留言
	
	public Integer update(BlogMsg blogMsg);  //修改留言
	
	public Integer delete(Integer blogMsgId); //刪除留言
	
	public BlogMsg getByPK(Integer blogMsgId);  //查詢單筆
	
	public List<BlogMsg>gatAll(); //找全部
	  
}
