package com.lazytravel.customerservice.service;

import java.util.List;

import com.lazytravel.customerservice.entity.CSMail;

public interface CSMailService {

	Integer addCSMail(CSMail csMail);

	Integer updateCSMail(CSMail csMail);

	CSMail getCSMailByCSMailId(Integer mailId);

	List<CSMail> getAllCSMails();
	
   CSMail findByCustomerId(Integer customerId);
	
	List<CSMail> getCSMailByCustomerId(Integer customerId);

}
