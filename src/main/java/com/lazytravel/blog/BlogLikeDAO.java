package com.lazytravel.blog;

import java.util.List;

public interface BlogLikeDAO {

	void add(BlogLike bloglike);
	void update(BlogLike bloglike);
	void delete(Integer bloglikeid);
	BlogLike getBlogLikeBybloglikeid(Integer bloglikeid);
	List<BlogLike>getAll();
}
