package com.lazytravel.customerservice.service;

import java.util.List;

import com.lazytravel.customerservice.dao.CSMailDAO;
import com.lazytravel.customerservice.dao.CSMailDAOImpl;
import com.lazytravel.customerservice.entity.CSMail;



public class CSMailServiceImpl implements CSMailService {

	private CSMailDAO dao;

	public CSMailServiceImpl() {
		dao = new CSMailDAOImpl();
	}

	@Override
	public Integer addCSMail(CSMail csMail) {
		Integer mailId = dao.add(csMail);
//		csMessage.setCsMail(csMail);
//		msgDao.add(csMessage);
//		csMail = dao.getByPK(id);
		return mailId;
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
	
	@Override
	public CSMail findByCustomerId(Integer customerId) {
		return dao.findByCustomerId(customerId);
	}

	@Override
	public List<CSMail> getCSMailByCustomerId(Integer customerId) {
		return  dao.getCSMailByCustomerId(customerId);
	}

}
