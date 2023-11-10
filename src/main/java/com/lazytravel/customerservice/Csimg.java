package com.lazytravel.customerservice;

import java.io.Serializable;
import java.sql.Timestamp;

public class Csimg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer imgId;
	private Integer messageId;
	private Byte  img;
	private Timestamp  createTime;
	
	public Csimg() {
		super();
	}

	public Csimg(Integer imgId, Integer messageId, Byte img, Timestamp createTime) {
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

	public Byte getImg() {
		return img;
	}

	public void setImg(Byte img) {
		this.img = img;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}