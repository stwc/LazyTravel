package com.lazytravel.blog;

import java.util.List;

public interface BlogMsgDAO {

	void add(BlogMsg blogMsg);
	void update(BlogMsg blogMsg);
	void delete(Integer blogMsgId);
	BlogMsg getBlogMsgByBlogMsgId(Integer blogMsgId);
	List<BlogMsg>gatALL();
	
}
