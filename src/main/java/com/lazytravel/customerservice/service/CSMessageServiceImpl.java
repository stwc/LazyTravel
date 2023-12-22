package com.lazytravel.customerservice.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lazytravel.customerservice.dao.CSMessageDAO;
import com.lazytravel.customerservice.dao.CSMessageDaoImpl;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.entity.CSMessage;
import com.lazytravel.util.HibernateUtil;

public class CSMessageServiceImpl implements CSMessageService {

	private CSMessageDAO dao;

	public CSMessageServiceImpl() {
		dao = new CSMessageDaoImpl();
	}

	@Override
	public CSMessage addCSMessage(CSMessage csMessage) {
		Integer id = dao.add(csMessage);
		csMessage = dao.getByPK(id);
		return csMessage;
	}

	@Override
	public Integer updateCSMessage(CSMessage csMessage) {
		return dao.update(csMessage);
	}

	@Override
	public CSMessage  getCSMessageByCSMessageId(Integer csMessageId) {
		return dao.getByPK(csMessageId);
	}

	@Override
	public List<CSMessage> getAllCSMessages() {
		return dao.getAll();
	}


}
