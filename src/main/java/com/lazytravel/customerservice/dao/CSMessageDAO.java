package com.lazytravel.customerservice.dao;

import java.util.List;

import com.lazytravel.customerservice.entity.CSMessage;


public interface CSMessageDAO {
public Integer add(CSMessage message); //新增
	
	public Integer update(CSMessage message); //修改
	
	public CSMessage getByPK(Integer messageId); //查詢
	
	public List<CSMessage>getAll(); //找全部

//    void add(CSMessage csMessage);
//
//    void update(CSMessage csMessage);
//
//    void delete(Integer messageId);
//
//    CSMessage findByPK(Integer messageId);
//
//    List<CSMessage> getAll();
}
