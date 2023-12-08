package com.lazytravel.order.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passenger_id", updatable = false)
	private Integer passengerId;
	
	@Column(name = "order_id" )
	private Integer orderId;
	
	@Column(name = "idno" , columnDefinition = "char")
	private String idno;
	
	@Column(name = "passenger_name" , columnDefinition = "varchar")
	private String passengerName;
	
	@Column(name = "phone" , columnDefinition = "char")
	private String phone;
	
	@Column(name = "birth" )
	private Date birth;
	
		public Passenger() {
		super();
	}


	public Passenger(Integer passengerId, Integer orderId, String idno, String passengerName, String phone,
			Date birth) {
		super();
		this.passengerId = passengerId;
		this.orderId = orderId;
		this.idno = idno;
		this.passengerName = passengerName;
		this.phone = phone;
		this.birth = birth;
	}


	public Integer getPassengerId() {
		return passengerId;
	}


	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public String getIdno() {
		return idno;
	}


	public void setIdno(String idno) {
		this.idno = idno;
	}


	public String getPassengerName() {
		return passengerName;
	}


	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", orderId=" + orderId + ", idno=" + idno + ", passengerName="
				+ passengerName + ", phone=" + phone + ", birth=" + birth + "]";
	}
	
	
	
}
