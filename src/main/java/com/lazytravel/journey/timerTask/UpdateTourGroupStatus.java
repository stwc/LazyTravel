package com.lazytravel.journey.timerTask;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lazytravel.journey.dao.TourGroupService;
import com.lazytravel.journey.dao.TourGroupServiceImpl;
import com.lazytravel.journey.entity.TourGroup;

public class UpdateTourGroupStatus extends TimerTask {
	private static final Logger logger = LoggerFactory.getLogger(UpdateTourGroupStatus.class);
	
	private ServletContext context;
	
	public UpdateTourGroupStatus(ServletContext context) {
		this.context = context;
	}
	
	public void run() {
		int count = 0;   // 計算筆數
		
        try {
    		TourGroupService tourGroupSvc = new TourGroupServiceImpl();
    		List<TourGroup> tourGroupList = tourGroupSvc.getAll();
    		
        	Timestamp startTime = new Timestamp(System.currentTimeMillis());
        	logger.info("UpdateTourGroupStatus 開始時間: " + startTime);
        	
        	List<TourGroup> tourGroupListUpdate = new ArrayList<>();
    		for(TourGroup tourGroup : tourGroupList) {
    		    Integer compareSignStart = startTime.compareTo(tourGroup.getSignupDate());
    		    Integer compareSignEnd = startTime.compareTo(tourGroup.getDueDate());
    			
    		    if(tourGroup.getGroupStatus().equals("0")) {
        		    if (compareSignStart >= 0 && compareSignEnd <= 0 ) {
	    		    	tourGroup.setGroupStatus("1");
	    		    	tourGroupListUpdate.add(tourGroup);
	    		    	logger.info("旅行團ID:" + tourGroup.getGroupId() + " 狀態改為已上架");
	    		    	count++;
        		    }
    		    }
    		    
    		    if(tourGroup.getGroupStatus().equals("1")) {
        		    if (compareSignStart < 0 || compareSignEnd > 0 ) {
	    		    	tourGroup.setGroupStatus("0");
	    		    	tourGroupListUpdate.add(tourGroup);
	    		    	logger.info("旅行團ID:" + tourGroup.getGroupId() + " 狀態改為未上架");
	    		    	count++;
        		    }
    		    }
    		}
    		
    		// 一次修改資料庫中的所有tourGroup物件
    		tourGroupSvc.updateList(tourGroupListUpdate);
    		logger.info("tourGroup資料庫修改完成，共執行: " + count + "筆");
    		
    		Timestamp endTime = new Timestamp(System.currentTimeMillis());
    		logger.info("UpdateTourGroupStatus 完成時間: " + endTime);
    		logger.info("------------------------------------------------------");
            
        } catch (Exception e) {
            logger.error("發生例外", e.getMessage());
        }
		
	}
	
}
