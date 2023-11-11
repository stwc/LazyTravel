package com.lazytravel.blog;

import java.util.List;

public interface BlogTagDAO {

	void add(BlogTag blogTag);
	void update(BlogTag blogTag);
	void delete(Integer blogId,Integer tagId);
	BlogTag getBlogTagBy(Integer blogId,Integer tagId);
	List<BlogTag>getAll();
}
