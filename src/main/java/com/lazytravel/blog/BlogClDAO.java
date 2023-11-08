package com.lazytravel.blog;

import java.util.List;

public interface BlogClDAO {

	void add(BlogCl blogcl);
	void update(BlogCl blogcl);
	void delete(Integer blogclid);
	
	BlogClDAO getBlogClDAOByBlogClDAOid(Integer blogclid);
	List<BlogClDAO>getAll();
}
