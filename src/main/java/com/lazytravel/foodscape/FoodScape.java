package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;

public class FoodScape implements Serializable {
	private Integer foodScapeId;
	private char name;
	private char phone;
	private String address;
	private String city;
	private double lng;
	private double lat;
	private String intro;
	private Date updateTime;
	private char status;
	private String category;
	
	
public  FoodScape() {
	
}



public FoodScape(Integer foodScapeId, char name, char phone, String address, String city, double lng, double lat,
		String intro, Date updateTime, char status, String category) {
	super();
	this.foodScapeId = foodScapeId;
	this.name = name;
	this.phone = phone;
	this.address = address;
	this.city = city;
	this.lng = lng;
	this.lat = lat;
	this.intro = intro;
	this.updateTime = updateTime;
	this.status = status;
	this.category = category;
}



public Integer getFoodScapeId() {
	return foodScapeId;
}


public void setFoodScapeId(Integer foodScapeId) {
	this.foodScapeId = foodScapeId;
}


public char getName() {
	return name;
}


public void setName(char name) {
	this.name = name;
}


public char getPhone() {
	return phone;
}


public void setPhone(char phone) {
	this.phone = phone;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public String getCity() {
	return city;
}


public void setCity(String city) {
	this.city = city;
}


public double getLng() {
	return lng;
}


public void setLng(double lng) {
	this.lng = lng;
}


public double getLat() {
	return lat;
}


public void setLat(double lat) {
	this.lat = lat;
}


public String getIntro() {
	return intro;
}


public void setIntro(String intro) {
	this.intro = intro;
}


public Date getUpdateTime() {
	return updateTime;
}


public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}


public char getStatus() {
	return status;
}


public void setStatus(char status) {
	this.status = status;
}


public String getCategory() {
	return category;
}


public void setCategory(String category) {
	this.category = category;
}
	

}
