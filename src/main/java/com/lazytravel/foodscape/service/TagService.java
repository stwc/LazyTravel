package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.entity.Tag;

public interface TagService {
	
	void addTag(Tag tag);
	
	void updateTag(Tag tag);
	
//	void deleteTag(Integer tagId);
	
	Tag getTagByTagId(Integer tagId);
	
	List<Tag> getAllTags();

}
