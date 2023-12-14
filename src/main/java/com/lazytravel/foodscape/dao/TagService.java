package com.lazytravel.foodscape.dao;

import java.util.List;

import com.lazytravel.foodscape.entity.Tag;

public interface TagService {
	
	Tag insertTag(Tag tag);
	
	Tag updateTag(Tag tag);
	
//	void deleteTag(Integer tagId);
	
	Tag getTagByTagId(Tag tagId);
	
	List<Tag> getAllTags(int currentPage);

}
