package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogImg;

public interface BlogClDAO {

	void insert(BlogCl entity);

	void update(BlogCl entity);

	void delete(Integer blogClId);

	BlogCl getByPK(Integer blogClId);

	List<BlogCl> getAll();

	List<BlogCl> getAll(int currentPage);

	long getTotal();
}
