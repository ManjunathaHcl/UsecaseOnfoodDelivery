package com.homelyfooduser.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homelyfooduser.dto.OrderDetailsDto;
import com.homelyfooduser.service.OrderHistoryService;

@RestController
public class OrderHistoryController {

	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@GetMapping("/orders")
	public List<OrderDetailsDto> getOrderDetials(){
		return orderHistoryService.getOrderHistory();
		
	}
	
}
