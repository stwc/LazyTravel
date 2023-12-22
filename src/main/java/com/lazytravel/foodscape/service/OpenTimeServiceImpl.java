package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.dao.OpenTimeDAO;
import com.lazytravel.foodscape.dao.OpenTimeDAOImpl;
import com.lazytravel.foodscape.entity.OpenTime;


public class OpenTimeServiceImpl implements OpenTimeService {
    private final OpenTimeDAO dao;

    public OpenTimeServiceImpl() {
        dao = new OpenTimeDAOImpl();
    }

    public void addOpenTime(OpenTime opentime) {
        dao.add(opentime);
    }

    public void updateOpenTime(OpenTime opentime) {
        dao.update(opentime);
    }

    public OpenTime getOpenTimeByOpenTimeId(Integer openTimeId) {
        return dao.getByPK(openTimeId);
    }

    public List<OpenTime> getAllOpenTimes() {
        return dao.getAll();
    }

}
