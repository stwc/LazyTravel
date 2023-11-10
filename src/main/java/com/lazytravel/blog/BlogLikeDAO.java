package com.lazytravel.blog;

import java.util.List;

public interface BlogLikeDAO {

	void add(BlogLike blogLike);
	void update(BlogLike blogLike);
	void delete(Integer blogLikeId);
	BlogLike getBlogLikeByBlogLikeId(Integer blogLikeId);
	List<BlogLike>getAll();
}
