package com.lazytravel.blog;

import java.util.List;

public interface BlogTagDAO {

	void add(BlogTag blogtag);
	void update(BlogTag blogtag);
	void delete(Integer tagid);
	BlogTag getBlogTagByTagid(Integer tagid);
	List<BlogTag>getAll();
}
