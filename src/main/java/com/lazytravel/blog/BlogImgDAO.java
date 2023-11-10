package com.lazytravel.blog;

import java.util.List;

public interface BlogImgDAO {
	
	void add(BlogImg blogImg);
	void update(BlogImg blogImg);
	void deledte(Integer blogImgId);
	BlogImg getBlogImgByBlogImgId(Integer blogImgId);
	List<BlogImg>getAll();
}
