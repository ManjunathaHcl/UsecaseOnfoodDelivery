package com.homelyfooduser.contoller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homelyfooduser.dto.OrderDto;
import com.homelyfooduser.dto.ProviderFoodItemsDto;
import com.homelyfooduser.dto.ResponseDto;
import com.homelyfooduser.service.PlaceOrderServiceImpl;


@RestController
public class PlaceOrderController {

	@Autowired
	PlaceOrderServiceImpl placeOrderService;

	@GetMapping("/items/{foodName}")
	public List<ProviderFoodItemsDto> getFoodItemsByName(@PathVariable @NotEmpty String foodName) {

		return placeOrderService.getFoodItemsByName(foodName);

	}

	@PostMapping("/placeOrder")
	public  ResponseDto placeOrder(@Valid @RequestBody OrderDto orderDto) {

		return placeOrderService.placeOrder(orderDto);

	}

}
