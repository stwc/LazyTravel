package com.lazytravel.foodscape.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.lazytravel.blog.entity.Blog;



@Entity
@Table(name = "foodscape")
public class FoodScape  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FOODSCAPE_ID",updatable = false)	
	private Integer foodScapeId;
	
	@OneToMany(mappedBy = "foodScapeId", cascade = CascadeType.ALL)
	private Set<OpenTime> opentimes;
	
	@OneToMany(mappedBy = "foodScapeId", cascade = CascadeType.ALL)
	private Set<FoodScapeImg> foodscapeimgs;
	
	@Column(name="FOODSCAPE_NAME")
	private String foodScapeName;
	
	@Column(name="PHONE", columnDefinition = "char")
	private String phone;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="LNG")
	private double lng;
	
	@Column(name="LAT")
	private double lat;
	
	@Column(name="INTRO",columnDefinition = "LONGTEXT")
	private String intro;
	
	@UpdateTimestamp
	@Column(name="UPDATE_TIME")
	private Timestamp updateTime;
	
	@Column(name="FOODSCAPE_STATUS",columnDefinition = "char")
	private String foodScapeStatus;
	
	@Column(name="CATEGORY")
	private String category;
	
	@ManyToMany
	@JoinTable(
				name = "foodscape_tag",
				joinColumns = { @JoinColumn(name = "FOODSCAPE_ID", referencedColumnName = "FOODSCAPE_ID")},
				inverseJoinColumns = { @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")}
			)
	private Set<Tag> tags;
	
	@ManyToMany
	@JoinTable(
				name = "foodscape_blog",
				joinColumns = { @JoinColumn(name = "FOODSCAPE_ID", referencedColumnName = "FOODSCAPE_ID")},
				inverseJoinColumns = { @JoinColumn(name = "BLOG_ID", referencedColumnName = "BLOG_ID")}
			)
	private Set<Blog> blogs;
	
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

public Set<OpenTime> getOpenTimes(){
	return opentimes;
}

public void setOpenTimes(Set<OpenTime> opentimes) {
	this.opentimes = opentimes;
}

public Set<FoodScapeImg> getFoodScapeImgs(){
	return foodscapeimgs;
}

public void setFoodScapeImgs(Set<FoodScapeImg> foodscapeimgs) {
	this.foodscapeimgs = foodscapeimgs;
}

public String getFoodScapeName() {
	return foodScapeName;
}


public void setFoodScapeName(String foodScapeName) {
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


public String getFoodScapeStatus() {
	return foodScapeStatus;
}


public void setFoodScapeStatus(String foodScapeStatus) {
	this.foodScapeStatus = foodScapeStatus;
}


public String getCategory() {
	return category;
}


public void setCategory(String category) {
	this.category = category;
}

public Set<Tag> getTags() {
	return tags;
}
public void setMembers(Set<Tag> tags) {
	this.tags = tags;
}

@Override
public String toString() {
	return "FoodScape [foodScapeId=" + foodScapeId + ", foodScapeName=" + foodScapeName + ", phone=" + phone
			+ ", address=" + address + ", city=" + city + ", lng=" + lng + ", lat=" + lat + ", intro=" + intro
			+ ", updateTime=" + updateTime + ", foodScapeStatus=" + foodScapeStatus + ", category=" + category + "]";
}
	

}
