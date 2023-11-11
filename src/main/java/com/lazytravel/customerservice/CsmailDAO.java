package com.lazytravel.customerservice;

import java.util.List;

public interface CsmailDAO {
	void add(Csmail csmail);
	void update(Csmail csmail);
	void delete(Integer mailId);
	Csmail findByPK(Integer mailID);
	List<Csmail>getAll();
	
}
<<<<<<< HEAD
=======


>>>>>>> refs/heads/master
