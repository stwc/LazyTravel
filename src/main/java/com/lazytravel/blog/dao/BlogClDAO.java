package com.lazytravel.blog;

import java.util.List;

public interface BlogClDAO {

	void add(BlogCl blogCl);
	void update(BlogCl blogCl);
	void delete(Integer blogClId);
	
	BlogCl getBlogClByBlogClId(Integer blogClId);
	List<BlogCl>getAll();
}
