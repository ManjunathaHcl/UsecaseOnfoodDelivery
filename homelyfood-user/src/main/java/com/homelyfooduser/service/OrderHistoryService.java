package com.homelyfooduser.service;

import java.util.List;

import com.homelyfooduser.dto.OrderDetailsDto;

public interface OrderHistoryService {
	List<OrderDetailsDto> getOrderHistory();

}
