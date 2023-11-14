package com.lazytravel.blog;

import java.util.List;

public interface BlogDAO {

	void add(Blog blog);
	void update(Blog blog);
	void delete(Integer blogId);
	Blog getBolgbyblogId(Integer blogId);
	List<Blog>getALL();
}
