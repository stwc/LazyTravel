package com.lazytravel.customerservice.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lazytravel.customerservice.dao.CSMailDAO;
import com.lazytravel.customerservice.dao.CSMailDAOImpl;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.util.HibernateUtil;

public class CSMailServiceImpl implements CSMailService {

	private CSMailDAO dao;

	public CSMailServiceImpl() {
		dao = new CSMailDAOImpl();
	}

	@Override
	public CSMail addCSMail(CSMail csMail) {
		Integer id = dao.add(csMail);
		csMail = dao.getByPK(id);
		return csMail;
	}

	@Override
	public Integer updateCSMail(CSMail csMail) {
		return dao.update(csMail);

	}

	@Override
	public CSMail getCSMailByCSMailId(Integer csMailId) {
		return dao.getByPK(csMailId);
	}

	public List<CSMail> getAllCSMails() {
		return dao.getAll();
	}
}
