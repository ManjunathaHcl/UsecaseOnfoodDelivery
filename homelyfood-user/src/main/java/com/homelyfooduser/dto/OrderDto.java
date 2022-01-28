package com.homelyfooduser.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class OrderDto {
	@Min(value = 0, message = "Please select positive numbers Only for userId")
	@Max(value = 666666, message = "Please enter valid  userId")
	private Integer userId;

	@NotEmpty(message = "accountNumber should not be empty")
	private String accountNumber;
	
	@Min(value = 0, message = "Please select positive numbers Only for providerId")
	@Max(value = 666666, message = "Please enter valid  providerId ")
	private Integer providerId;

	@Valid
	private List<FoodItemDtls> foodItemDtls;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<FoodItemDtls> getFoodItemDtls() {
		return foodItemDtls;
	}

	public void setFoodItemDtls(List<FoodItemDtls> foodItemDtls) {
		this.foodItemDtls = foodItemDtls;
	}

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

}
