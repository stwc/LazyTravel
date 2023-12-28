package com.lazytravel.customerservice;

public enum CSMessageFrom {
	
	CUSTOMER("0"),  
    CUSTOMER_SERVICE_STAFF("1");
	
	
	
	
	private String messageFrom;  
	
	CSMessageFrom(String typeName) {  
        this.messageFrom = typeName;  
    }  
	
	
	public String getMessageFrom() {  
        return this.messageFrom;  
    }   
	
}
