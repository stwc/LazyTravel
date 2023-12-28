package com.lazytravel.util;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static String formate(Timestamp time) {
		if(time == null) {
			return "";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String formattedDateTime = time.toLocalDateTime().format(formatter);
		return formattedDateTime;
	}
	
	

}
