package com.lazytravel.customerservice.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lazytravel.customerservice.dao.CSImgDAO;
import com.lazytravel.customerservice.dao.CSImgDAOImpl;
import com.lazytravel.customerservice.entity.CSImg;
import com.lazytravel.util.HibernateUtil;

public class CSImgServiceImpl implements CSImgService {

	private CSImgDAO dao;

	public CSImgServiceImpl() {
		dao = new CSImgDAOImpl();
	}

	@Override
	public CSImg addCSImg(CSImg csImg) {
		Integer id = dao.add(csImg);
		csImg = dao.getByPK(id);
		return csImg;
	}

	@Override
	public Integer updateCSImg(CSImg csImg) {
		return dao.update(csImg);
	}

	@Override
	public CSImg getCSImgByCSImgId(Integer csImgId) {
		return dao.getByPK(csImgId);
	}

	@Override
	public List<CSImg> getAllCSImgs() {
		return dao.getAll();
	}

}
