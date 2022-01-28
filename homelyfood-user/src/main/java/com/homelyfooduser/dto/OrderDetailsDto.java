package com.homelyfooduser.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.homelyfooduser.entity.OrderItems;

public class OrderDetailsDto {

	private String orderId;

	private Integer providerId;

	private Integer userId;
	private Integer totalOrderAmount;

	private Long accountNumber;

	
	private String orderStatus;
	private LocalDateTime orderDate;
	
	private List<OrderItems> foodItems;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTotalOrderAmount() {
		return totalOrderAmount;
	}

	public void setTotalOrderAmount(Integer totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItems> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(List<OrderItems> foodItems) {
		this.foodItems = foodItems;
	}
	
	
	
}
