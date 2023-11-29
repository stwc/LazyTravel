package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogLike;

public interface BlogLikeDAO {

	void insert(BlogLike entity);

	void update(BlogLike entity);

	void delete(Integer blogLikeId);

	BlogLike getByPK(Integer blogLikeId);

	List<BlogLike> getAll();

	List<BlogLike> getAll(int currentPage);

	long getTotal();
}
