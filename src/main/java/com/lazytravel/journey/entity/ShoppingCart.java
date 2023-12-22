package com.lazytravel.journey.entity;

import java.io.Serializable;
import java.util.Objects;

public class ShoppingCart implements Serializable {
	private String customerId;
	private String groupId;
	private Integer quantity;
	
	public ShoppingCart() {
		super();
	}
	
	public ShoppingCart(String customerId, String groupId, Integer quantity) {
		super();
		this.customerId = customerId;
		this.groupId = groupId;
		this.quantity = quantity;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ShoppingCart [customerId=" + customerId + ", groupId=" + groupId + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, groupId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCart other = (ShoppingCart) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(groupId, other.groupId);
	}
	
}
