package com.lazytravel.customerservice;

import java.util.List;

public interface CsimgDAO {
		void add(Csimg csimg);
		void update(Csimg csimg);
		void delete(Integer imgId);
		Csimg findByPK(Integer imgId);
		List<Csimg>getAll();
}