package com.lazytravel.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.lazytravel.journey.timerTask.UpdateTourGroupStatus;
import com.lazytravel.util.HibernateUtil;

@WebListener
public class InitializerListener implements ServletContextListener {
	
	private Timer timer;
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext started");
        HibernateUtil.getSessionFactory();
        
        // 啟動timer，每分鐘檢查一次UpdateTourGroupStatus
		timer = new Timer();
        
		UpdateTourGroupStatus updateTourGroupStatus = new UpdateTourGroupStatus(sce.getServletContext());
        
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 計算距離下一個 0 秒需延遲時間的毫秒數
        long initialDelay = (60 - calendar.get(Calendar.SECOND)) * 1000;
        Date nextZeroSecond = new Date(date.getTime() + initialDelay);
        
        timer.scheduleAtFixedRate(updateTourGroupStatus, nextZeroSecond, 1000 * 60);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext ended");
        HibernateUtil.shutdown();
        
        timer.cancel();
    }
    

}
