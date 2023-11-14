package com.lazytravel.customerservice;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class CSImg implements Serializable {
    private Integer imgId;
    private Integer messageId;
    private byte[] img;
    private Timestamp createTime;

    public CSImg() {
        super();
    }

    public CSImg(Integer imgId, Integer messageId, byte[] img, Timestamp createTime) {
        super();
        this.imgId = imgId;
        this.messageId = messageId;
        this.img = img;
        this.createTime = createTime;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CSImg{" +
                "imgId=" + imgId +
                ", messageId=" + messageId +
                ", img=" + Arrays.toString(img) +
                ", createTime=" + createTime +
                '}';
    }
}
