package com.lazytravel.customerservice.dao;

import java.util.List;

import com.lazytravel.customerservice.entity.CSMail;

public interface CSMailDAO {

	public Integer add(CSMail csMail); //新增
	
	public Integer update(CSMail csMail); //修改
	
	public CSMail getByPK(Integer mailId); //查詢
	
	public List<CSMail>getAll(); //找全部

	
	
//	void add(CSMail csMail);
//	void update(CSMail csMail);
//	void delete(Integer mailId);
//	CSMail findByPK(Integer mailId);
//	List<CSMail>getAll();
}