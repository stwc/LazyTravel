package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;

public class Tag implements Serializable {
	private Integer tagId;
	private String name;
	private Date updateTime; 
	

public Tag() {
	
}


public Tag(Integer tagId, String name, Date updateTime) {
	super();
	this.tagId = tagId;
	this.name = name;
	this.updateTime = updateTime;
}


public Integer getTagId() {
	return tagId;
}


public void setTagId(Integer tagId) {
	this.tagId = tagId;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public Date getUpdateTime() {
	return updateTime;
}


public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}



}