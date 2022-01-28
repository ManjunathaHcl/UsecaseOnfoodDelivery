package com.homelyfooduser.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class FoodItemDtls {

	@NotNull (message="itemId details shoiuld not be null")
	@Range(min = 0l, message = "Please select positive numbers Only for itemId")
	private Integer itemId;
	@NotNull (message="Quantity details shoiuld not be null")
	@Range(min = 0l, message = "Please select positive numbers Only for Quantity")	
	private Integer Quantity;
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return Quantity;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	
	
	
}
