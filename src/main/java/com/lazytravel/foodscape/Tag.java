package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Tag implements Serializable {
	private Integer tagId;
	private String tagName;
	private Timestamp updateTime; 
	

public Tag() {
	
}


public Tag(Integer tagId, String name, Timestamp updateTime) {
	super();
	this.tagId = tagId;
	this.tagName = tagName;
	this.updateTime = updateTime;
}


public Integer getTagId() {
	return tagId;
}


public void setTagId(Integer tagId) {
	this.tagId = tagId;
}


public String getName() {
	return tagName;
}


public void setName(String tagName) {
	this.tagName = tagName;
}


public Timestamp getUpdateTime() {
	return updateTime;
}


public void setUpdateTime(Timestamp updateTime) {
	this.updateTime = updateTime;
}



}