package com.lazytravel.foodscape;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class FoodScape implements Serializable {
	private Integer foodScapeId;
	private String foodScapeName;
	private String phone;
	private String address;
	private String city;
	private double lng;
	private double lat;
	private String intro;
	private Timestamp updateTime;
	private String foodScapeStatus;
	private String category;
	
	
public  FoodScape() {
	
}



public FoodScape(Integer foodScapeId, String foodScapeName, String phone, String address, String city, double lng, double lat,
		String intro, Timestamp updateTime, String foodScapeStatus, String category) {
	super();
	this.foodScapeId = foodScapeId;
	this.foodScapeName = foodScapeName;
	this.phone = phone;
	this.address = address;
	this.city = city;
	this.lng = lng;
	this.lat = lat;
	this.intro = intro;
	this.updateTime = updateTime;
	this.foodScapeStatus = foodScapeStatus;
	this.category = category;
}



public Integer getFoodScapeId() {
	return foodScapeId;
}


public void setFoodScapeId(Integer foodScapeId) {
	this.foodScapeId = foodScapeId;
}


public String getName() {
	return foodScapeName;
}


public void setName(String foodScapeName) {
	this.foodScapeName = foodScapeName;
}


public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
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


public Timestamp getUpdateTime() {
	return updateTime;
}


public void setUpdateTime(Timestamp updateTime) {
	this.updateTime = updateTime;
}


public String getStatus() {
	return foodScapeStatus;
}


public void setStatus(String foodScapeStatus) {
	this.foodScapeStatus = foodScapeStatus;
}


public String getCategory() {
	return category;
}


public void setCategory(String category) {
	this.category = category;
}
	

}
