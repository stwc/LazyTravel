//package com.lazytravel.customerservice.dao;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//public class CSMail implements Serializable {
//    private Integer mailId;
//    private Integer customerId;
//    private String title;
//    private Timestamp createTime;
//    private Timestamp lastMsgTime;
//    private String csMailStatus;
//
//    public CSMail() {
//        super();
//    }
//
//    public CSMail(Integer mailId, Integer customerId, String title, Timestamp createTime, Timestamp lastMsgTime,
//                  String csMailStatus) {
//        this.mailId = mailId;
//        this.customerId = customerId;
//        this.title = title;
//        this.createTime = createTime;
//        this.lastMsgTime = lastMsgTime;
//        this.csMailStatus = csMailStatus;
//    }
//
//    public Integer getMailId() {
//        return mailId;
//    }
//
//    public void setMailId(Integer mailId) {
//        this.mailId = mailId;
//    }
//
//    public Integer getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(Integer customerId) {
//        this.customerId = customerId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public Timestamp getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Timestamp createTime) {
//        this.createTime = createTime;
//    }
//
//    public Timestamp getLastMsgTime() {
//        return lastMsgTime;
//    }
//
//    public void setLastMsgTime(Timestamp lastMsgTime) {
//        this.lastMsgTime = lastMsgTime;
//    }
//
//    public String getCsMailStatus() {
//        return csMailStatus;
//    }
//
//    public void setCsMailStatus(String csMailStatus) {
//        this.csMailStatus = csMailStatus;
//    }
//
//    @Override
//    public String toString() {
//        return "CSMail{" +
//                "mailId=" + mailId +
//                ", customerId=" + customerId +
//                ", title='" + title + '\'' +
//                ", createTime=" + createTime +
//                ", lastMsgTime=" + lastMsgTime +
//                ", csMailStatus='" + csMailStatus + '\'' +
//                '}';
//    }
//}