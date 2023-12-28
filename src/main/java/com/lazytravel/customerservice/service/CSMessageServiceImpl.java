package com.lazytravel.customerservice.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customerservice.dao.CSMessageDAO;
import com.lazytravel.customerservice.dao.CSMessageDaoImpl;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.entity.CSMessage;
import com.lazytravel.util.HibernateUtil;

public class CSMessageServiceImpl implements CSMessageService {

	private CSMessageDAO dao;
	private CSMailService csMailService = new CSMailServiceImpl();
	private CustomerService customerService = new CustomerServiceImpl();

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

	@Override
	public List<CSMessage> findByCustomerId(Integer customerId) {
		return dao.findByCustomerId(customerId);
	}

	@Override
	public void saveCSMessage(CSMessage csMessage,Integer customerId) {
//		CSMail csMail = csMailService.findByCustomerId(customerId);
//		
//		if(csMail != null && csMail.getMailId() != null) {
//			System.out.println("csMail getMailId:"+csMail.getMailId());
//			csMessage.setCsMail(csMail);
//			
//			dao.add(csMessage);
//		} else {
//			System.out.println("create new CSMail");
//			Timestamp now = new Timestamp(System.currentTimeMillis());
//			csMail = new CSMail();
//			Customer customer = customerService.getOneCustomer(customerId);
//			csMail.setCustomer(customer);
//			csMail.setTitle("");
//			csMail.setCreateTime(now);
//			csMail.setLastMsgTime(now);
//			csMail.setCsMailStatus("0");
//			csMail.setCsMessageList(new ArrayList<CSMessage>(Arrays.asList(csMessage)));
//			
//			csMessage.setCsMail(csMail);
//			csMailService.addCSMail(csMail);
//		}
		
	}

	@Override
	public List<CSMessage> getCSMessageByMailId(Integer mailId) {
		return dao.getCSMessageByMailId(mailId);
	}

}
