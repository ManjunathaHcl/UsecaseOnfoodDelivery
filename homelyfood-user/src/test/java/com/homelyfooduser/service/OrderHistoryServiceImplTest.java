package com.homelyfooduser.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ctc.wstx.shaded.msv_core.reader.xmlschema.AnyState;
import com.homelyfooduser.dto.OrderDetailsDto;
import com.homelyfooduser.entity.OrderItems;
import com.homelyfooduser.entity.Orders;
import com.homelyfooduser.repository.OrderItemsRepository;
import com.homelyfooduser.repository.OrderRepository;

@ExtendWith(SpringExtension.class)
public class OrderHistoryServiceImplTest {
	@InjectMocks
	OrderHistoryServiceImpl orderHistoryServiceImpl;

	@Mock
	OrderRepository orderRepo;

	@Mock
	OrderItemsRepository orderItems;

	@BeforeEach
	public void init() {


		List<OrderItems> list = new ArrayList<OrderItems>();
		OrderItems oi = new OrderItems();
		oi.setItemId(5);
		oi.setName("chapati");
		oi.setOrderId("Order-12-46");
		oi.setPrice(40);
		oi.setQuantity(1);
		oi.setTotalAmount(40);
		List<Orders> listOrders = new ArrayList<Orders>();

		Orders order = new Orders();
		order.setAccountNumber(500014L);
		order.setOrderId("Order-12-46");
		order.setProviderId(1);
		order.setUserId(1);
		listOrders.add(order);
		list.add(oi);

		when(orderItems.findAllByOrderId(Matchers.any(String.class))).thenReturn(list);

		when(orderRepo.findOrdersByOrderDateBetween(Matchers.any(LocalDateTime.class),
				Matchers.any(LocalDateTime.class))).thenReturn(listOrders);

	}

	@Test
	public void testgetOrderHistory() {

		List<OrderDetailsDto> orderdtls = orderHistoryServiceImpl.getOrderHistory();

		assertEquals(orderdtls.size(), 1);

	}

}
