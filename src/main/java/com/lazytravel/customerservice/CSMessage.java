package com.lazytravel.customerservice;

import java.io.Serializable;
import java.sql.Timestamp;

public class CSMessage implements Serializable {
    private Integer messageId;
    private Integer mailId;
    private String content;
    private Timestamp createTime;
    private String messageFrom;

    public CSMessage() {
        super();

    }

    public CSMessage(Integer messageId, Integer mailId, String content, Timestamp createTime, String messageFrom) {
        super();
        this.messageId = messageId;
        this.mailId = mailId;
        this.content = content;
        this.createTime = createTime;
        this.messageFrom = messageFrom;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    @Override
    public String toString() {
        return "CSMessage{" +
                "messageId=" + messageId +
                ", mailId=" + mailId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", messageFrom='" + messageFrom + '\'' +
                '}';
    }
}
