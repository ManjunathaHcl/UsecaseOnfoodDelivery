package com.homelyfooduser.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homelyfooduser.dto.OrderDetailsDto;
import com.homelyfooduser.entity.Orders;
import com.homelyfooduser.repository.OrderItemsRepository;
import com.homelyfooduser.repository.OrderRepository;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	OrderRepository orderRepo;

	@Autowired
	OrderItemsRepository orderItems;

	@Override
	public List<OrderDetailsDto> getOrderHistory() {
		// TODO Auto-generated method stub
		List<OrderDetailsDto> OrderDetailsDtoList = new ArrayList<>();

		List<Orders> orderslist = orderRepo.findOrdersByOrderDateBetween(LocalDateTime.now().minusHours(1L),LocalDateTime.now());

		Iterator<Orders> orders = orderslist.listIterator();

		while (orders.hasNext()) {
			Orders order = orders.next();
			OrderDetailsDto OrderDetailsDto = new OrderDetailsDto();
			BeanUtils.copyProperties(order, OrderDetailsDto);
			OrderDetailsDto.setFoodItems(orderItems.findAllByOrderId(order.getOrderId()));
			OrderDetailsDtoList.add(OrderDetailsDto);
		}

		return OrderDetailsDtoList.stream().sorted(Comparator.comparing(OrderDetailsDto::getOrderDate).reversed()).collect(Collectors.toList());
	}

}
