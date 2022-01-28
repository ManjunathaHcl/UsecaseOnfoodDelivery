package com.homelyfooduser.service;

import java.util.List;

import com.homelyfooduser.dto.OrderDto;
import com.homelyfooduser.dto.ProviderFoodItemsDto;
import com.homelyfooduser.dto.ResponseDto;
import com.homelyfooduser.entity.ProviderMenu;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface PlaceOrderService {
	
	 List<ProviderFoodItemsDto> getFoodItemsByName( String foodName );
	
	 ResponseDto  placeOrder(@RequestBody OrderDto orderDto);
	

}
