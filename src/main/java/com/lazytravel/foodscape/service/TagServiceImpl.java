package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.dao.TagDAO;
import com.lazytravel.foodscape.dao.TagDAOImpl;
import com.lazytravel.foodscape.entity.Tag;


public class TagServiceImpl implements TagService {
    private final TagDAO dao;

    public TagServiceImpl() {
        dao = new TagDAOImpl();
    }

    public void addTag(Tag tag) {
        dao.add(tag);
    }

    public void updateTag(Tag tag) {
        dao.update(tag);
    }

    public Tag getTagByTagId(Integer tagId) {
        return dao.getByPK(tagId);
    }

    public List<Tag> getAllTags() {
        return dao.getAll();
    }


}
