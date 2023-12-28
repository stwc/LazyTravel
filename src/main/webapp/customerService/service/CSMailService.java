package com.lazytravel.customerservice.service;

import java.util.List;

import com.lazytravel.customerservice.entity.CSCustomerVO;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.entity.CSMessage;

public interface CSMailService {

	CSMail addCSMail(CSMail csMail, CSMessage csMessage);

	Integer updateCSMail(CSMail csMail);

	CSMail getCSMailByCSMailId(Integer mailId);

	List<CSMail> getAllCSMails();
	
   CSMail findByCustomerId(Integer customerId);
	
	public List<CSCustomerVO> getCSCustomerList();
	
	List<CSMail> getCSMailByCustomerId(Integer customerId);

}
