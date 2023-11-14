package com.lazytravel.customerservice;

import java.util.List;

public interface CSMailDAO {
	void add(CSMail csMail);
	void update(CSMail csMail);
	void delete(Integer mailId);
	CSMail findByPK(Integer mailID);
	List<CSMail>getAll();
	
}