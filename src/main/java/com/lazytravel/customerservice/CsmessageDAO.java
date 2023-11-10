package com.lazytravel.customerservice;

import java.util.List;

public interface CsmessageDAO{
	void add(Csmessage csmessage);
	void update(Csmessage csmessage);
	void delete(Integer messageId);
	Csmessage findByPK(Integer messageId);
	List<Csmessage>getAll();
}
