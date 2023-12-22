package com.lazytravel.foodscape.entity;

import java.sql.Timestamp;
import java.util.HashSet;
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
import javax.persistence.Table;

import com.lazytravel.blog.entity.Blog;


@Entity
@Table(name= "tag")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="TAG_ID",updatable = false)
	private Integer tagId;
	
	@Column(name ="TAG_NAME")
	private String tagName;
	
	@Column(name ="UPDATE_TIME")
	private Timestamp updateTime; 
	
//	@ManyToMany
//	@JoinTable(
//				name = "foodscape_tag",
//				joinColumns = { @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")},
//				inverseJoinColumns = { @JoinColumn(name = "FOODSCAPE_ID", referencedColumnName = "FOODSCAPE_ID")}
//			)
//	private Set<FoodScape> foodscapes;
	
	@ManyToMany (mappedBy = "tags")
    private Set<Blog> blogs = new HashSet<>();

public Tag() {
	
}

public Integer getTagId() {
	return tagId;
}

public void setTagId(Integer tagId) {
	this.tagId = tagId;
}


public String getTagName() {
	return tagName;
}


public void setTagName(String tagName) {
	this.tagName = tagName;
}

public Timestamp getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(Timestamp updateTime) {
	this.updateTime = updateTime;
}


public Set<FoodScape> getFoodScapes() {
	return foodscapes;
}
public void setFoodScapes(Set<FoodScape> foodscapes) {
	this.foodscapes = foodscapes;
}

//public void getCreateTime(Timestamp timestamp) {
//	return createTime;
//	
//}
//
//public void setCreateTime(Timestamp timestamp) {
//	this.createTime = createTime;
//	
//}


@Override
public String toString() {
	return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", updateTime=" + updateTime + "]";
}

//
//public Tag(Integer tagId, String name, Timestamp updateTime) {
//	super();
//	this.tagId = tagId;
//	this.tagName = tagName;
//	this.updateTime = updateTime;
//}
//
//
//public Integer getTagId() {
//	return tagId;
//}
//
//
//public void setTagId(Integer tagId) {
//	this.tagId = tagId;
//}
//
//
//public String getName() {
//	return tagName;
//}
//
//
//public void setName(String tagName) {
//	this.tagName = tagName;
//}
//
//
//public Timestamp getUpdateTime() {
//	return updateTime;
//}
//
//
//public void setUpdateTime(Timestamp updateTime) {
//	this.updateTime = updateTime;
//}
//
//public Set<FoodScape> getFoodScapes() {
//	return foodscapes;
//}
//public void setFoodScapes(Set<FoodScape> foodscapes) {
//	this.foodscapes = foodscapes;
//}
//
//@Override
//public String toString() {
//	return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", updateTime=" + updateTime + "]";
//}






}