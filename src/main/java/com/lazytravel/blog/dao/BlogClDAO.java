package com.lazytravel.blog.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.customer.entity.Customer;

public interface BlogClDAO {

	public Integer add(BlogCl blogCl); //新增收藏文章

	public Integer update(BlogCl blogCl); //修改收藏文章
	
	public BlogCl getByPK(Integer blogClId); //用會員ID查詢收藏文章
	
	public List<BlogCl>getAll();
	
	public String isBlogCl(Integer customerId,Integer blogId) ;
	
	String addFavoriteCl(Integer customerId,Integer blogId);
	
	String unFavoriteCl(Integer customerId,Integer blogId);
	
	String updateFavoriteCl(Integer customerId,Integer blogId);
	
	public List<BlogCl> getBlogClByCustomerId(Integer customerId);
	
	String blogClStatus(Blog blog,Customer customer);
}
