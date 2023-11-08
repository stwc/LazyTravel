package com.lazytravel.blog;

import java.util.List;

public interface BlogImgDAO {
	
	void add(BlogImgDAO blogimgdao);
	void update(BlogImgDAO blogimgdao);
	void deledte(Integer blogimgid);
	BlogImgDAO getBlogImgDAOByBlogImgid(Integer blogimgdaoid);
	List<BlogImgDAO>getAll();
}
