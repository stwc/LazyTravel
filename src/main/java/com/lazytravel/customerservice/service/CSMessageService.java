package com.lazytravel.customerservice.service;

import java.util.List;

import com.lazytravel.customerservice.entity.CSMessage;

public interface CSMessageService {
	
	CSMessage addCSMessage(CSMessage csMessage);

	Integer updateCSMessage(CSMessage csMessage);

	CSMessage getCSMessageByCSMessageId(Integer csMessageId);

	List<CSMessage> getAllCSMessages();

}
