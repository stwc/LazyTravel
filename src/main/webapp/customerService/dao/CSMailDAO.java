package com.lazytravel.customerservice.dao;

import java.util.List;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.customerservice.entity.CSCustomerVO;
import com.lazytravel.customerservice.entity.CSMail;

public interface CSMailDAO {

	public Integer add(CSMail csMail); //新增
	
	public Integer update(CSMail csMail); //修改
	
	public CSMail getByPK(Integer mailId); //查詢
	
	public List<CSMail>getAll(); //找全部

   public CSMail findByCustomerId(Integer customerId);
	
	public List<CSCustomerVO> getCSCustomerList();

	public List<CSMail> getCSMailByCustomerId(Integer customerId);

}