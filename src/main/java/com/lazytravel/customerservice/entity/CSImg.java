//package com.lazytravel.customerservice.entity;
//
//import java.sql.Timestamp;
//import java.util.Arrays;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//
//import com.lazytravel.customerservice.entity.CSMessage;
//
//@Entity
//@Table(name = "CS_IMG")
//public class CSImg {
//	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name ="IMG_ID")
//    private Integer imgId;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="MESSAGE_ID",referencedColumnName = "MESSAGE_ID")
//    private CSMessage csMessage;
//	
//	@Column(name="IMG",columnDefinition = "LONGBLOB")
//    private byte[] img;
//	
//	@Column(name="CREATE_TIME")
//    private Timestamp createTime;
//
//    public CSImg() {
//    }
//
//	public Integer getImgId() {
//		return imgId;
//	}
//
//	public void setImgId(Integer imgId) {
//		this.imgId = imgId;
//	}
//
//
//	public CSMessage getCSMessage() {
//		return csMessage;
//	}
//
//	public void setCsMessage(CSMessage csMessage) {
//		this.csMessage = csMessage;
//	}
//
//	public byte[] getImg() {
//		return img;
//	}
//
//	public void setImg(byte[] img) {
//		this.img = img;
//	}
//
//	public Timestamp getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Timestamp createTime) {
//		this.createTime = createTime;
//	}
//
//	@Override
//	public String toString() {
//		return "CSImg [imgId=" + imgId + ", csMessage=" + csMessage + ", img=" + Arrays.toString(img) + ", createTime="
//				+ createTime + "]";
//	}
//
//
//    
//}
