package com.lazytravel.customerservice;

import java.util.List;

public interface CSImgDAO {
		void add(CSImg csiImg);
		void update(CSImg csimg);
		void delete(Integer imgId);
		CSImg findByPK(Integer imgId);
		List<CSImg>getAll();

}