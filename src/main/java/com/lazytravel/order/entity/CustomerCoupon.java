package com.lazytravel.order.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.lazytravel.order.entity.CustomerCoupon.CompositeDetail;


@Entity
@Table(name = "customer_coupon")
@IdClass(CompositeDetail.class)
public class CustomerCoupon {
	
	@Id 
	@Column(name = "customer_id")
	private Integer customerId;

	@Id 
	@Column(name = "coupon_id")
	private Integer couponId;
	
	@Column(name = "coupon_status" , columnDefinition = "char")
	private String couponStatus;
	
	@Column(name = "create_time")
	private Timestamp createTime;
	
	@Column(name = "update_time")
	private Timestamp updateTime;
	
	// 特別加上對複合主鍵物件的 getter / setter
		public CompositeDetail getCompositeKey() {
			return new CompositeDetail(customerId, couponId);
		}

		public void setCompositeKey(CompositeDetail key) {
			this.customerId = key.getcustomerId();
			this.couponId = key.getcouponId();
		}
		
		public Integer getCustomerId() {
			return customerId;
		}


		public void setCustomerId(Integer customerId) {
			this.customerId = customerId;
		}


		public Integer getCouponId() {
			return couponId;
		}


		public void setCouponId(Integer couponId) {
			this.couponId = couponId;
		}


		public String getCouponStatus() {
			return couponStatus;
		}


		public void setCouponStatus(String couponStatus) {
			this.couponStatus = couponStatus;
		}


		public Timestamp getCreateTime() {
			return createTime;
		}


		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}


		public Timestamp getUpdateTime() {
			return updateTime;
		}


		public void setUpdateTime(Timestamp updateTime) {
			this.updateTime = updateTime;
		}
		
		
	
		public static class CompositeDetail implements Serializable {
			private static final long serialVersionUID = 1L;

			private Integer customerId;
			private Integer couponId;
			
			// 一定要有無參數建構子
			public CompositeDetail() {
				super();
			}

			public CompositeDetail(Integer customerId, Integer couponId) {
				super();
				this.customerId = customerId;
				this.couponId = couponId;
			}

			public Integer getcustomerId() {
				return customerId;
			}

			public void setcustomerId(Integer customerId) {
				this.customerId = customerId;
			}

			public Integer getcouponId() {
				return couponId;
			}

			public void setcouponId(Integer couponId) {
				this.couponId = couponId;
			}

			// 一定要 override 此類別的 hashCode() 與 equals() 方法！
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
				result = prime * result + ((couponId == null) ? 0 : couponId.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;

				if (obj != null && getClass() == obj.getClass()) {
					CompositeDetail compositeKey = (CompositeDetail) obj;
					if (customerId.equals(compositeKey.customerId) && couponId.equals(compositeKey.couponId)) {
						return true;
					}
				}

				return false;
			}
			
			

		}
		
		
		@Override
	    public String toString() {
	        return "CustomerCoupon{" +
	                "customerId=" + customerId +
	                ", couponId=" + couponId +
	                ", couponStatus='" + couponStatus + '\'' +
	                ", createTime=" + createTime +
	                ", updateTime=" + updateTime +
	                '}';
	    }
		

	
	
	
	
//	public CustomerCoupon() {
//		super();
//	}
//	
//
//	public CustomerCoupon(Integer customerId, Integer couponId, String couponStatus, Timestamp createTime,
//			Timestamp updateTime) {
//		super();
//		this.customerId = customerId;
//		this.couponId = couponId;
//		this.couponStatus = couponStatus;
//		this.createTime = createTime;
//		this.updateTime = updateTime;
//	}
//
//
//	
//
//
//	@Override
//	public String toString() {
//		return "CustomerCoupon [customerId=" + customerId + ", couponId=" + couponId + ", couponStatus=" + couponStatus
//				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
//	}
	
	

}
