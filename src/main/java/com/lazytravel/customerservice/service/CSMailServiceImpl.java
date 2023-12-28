package com.lazytravel.customerservice.service;

import java.util.List;

import com.lazytravel.customerservice.dao.CSMailDAO;
import com.lazytravel.customerservice.dao.CSMailDAOImpl;
import com.lazytravel.customerservice.dao.CSMessageDAO;
import com.lazytravel.customerservice.dao.CSMessageDaoImpl;
import com.lazytravel.customerservice.entity.CSCustomerVO;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.entity.CSMessage;

public class CSMailServiceImpl implements CSMailService {

	private CSMailDAO dao;
	private CSMessageDAO msgDao;

	public CSMailServiceImpl() {
		dao = new CSMailDAOImpl();
		msgDao = new CSMessageDaoImpl();
	}

	@Override
	public CSMail addCSMail(CSMail csMail, CSMessage csMessage) {
		Integer id = dao.add(csMail);
		csMessage.setCsMail(csMail);
		msgDao.add(csMessage);
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
	
	@Override
	public CSMail findByCustomerId(Integer customerId) {
		return dao.findByCustomerId(customerId);
	}

	@Override
	public List<CSCustomerVO> getCSCustomerList() {
		return dao.getCSCustomerList();
	}

	@Override
	public List<CSMail> getCSMailByCustomerId(Integer customerId) {
		return  dao.getCSMailByCustomerId(customerId);
	}

}
