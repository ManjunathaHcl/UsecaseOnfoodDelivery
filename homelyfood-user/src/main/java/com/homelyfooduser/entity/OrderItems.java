package com.homelyfooduser.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderItemsId;

	private String orderId;

	private Integer itemId;

	private String Name;
	private Integer Quantity;
	private Integer price;
	private Integer totalAmount;
	public Integer getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(Integer orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public Integer getQuantity() {
		return Quantity;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
