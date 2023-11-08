package com.lazytravel.blog;

import java.util.List;

public interface BlogMsgDAO {

	void add(BlogMsg blogmsg);
	void update(BlogMsg blogmsg);
	void delete(Integer Blogmsgid);
	BlogMsg getBlogmsgByBlogmsgid(Integer Blogmsgid);
	List<BlogMsg>gatALL();
	
}
