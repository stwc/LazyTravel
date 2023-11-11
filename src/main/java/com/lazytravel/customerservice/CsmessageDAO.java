package com.lazytravel.customerservice;

import java.util.List;

public interface CSMessageDAO{
	void add(CSMessage csmessage);
	void update(CSMessage csmessage);
	void delete(Integer messageId);
	CSMessage findByPK(Integer messageId);
	List<CSMessage>getAll();
}
