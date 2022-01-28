package com.homelyfooduser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.homelyfooduser.dto.FoodItemDtls;
import com.homelyfooduser.dto.OrderDto;
import com.homelyfooduser.dto.ProviderFoodItemsDto;
import com.homelyfooduser.dto.ResponseDto;
import com.homelyfooduser.dto.TransactionDto;
import com.homelyfooduser.entity.OrderItems;
import com.homelyfooduser.entity.Orders;
import com.homelyfooduser.entity.Provider;
import com.homelyfooduser.entity.User;
import com.homelyfooduser.external.BankServiceClient;
import com.homelyfooduser.external.FoodProviderClient;
import com.homelyfooduser.repository.OrderItemsRepository;
import com.homelyfooduser.repository.OrderRepository;
import com.homelyfooduser.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class PlaceOrderServiceTest {

	@InjectMocks
	PlaceOrderServiceImpl placeOrderServiceImpl;

	@Mock
	OrderRepository orderRepo;

	@Mock
	OrderItemsRepository orderItems;

	@Mock
	FoodProviderClient foodproviderClient;

	@Mock
	BankServiceClient bankServiceClient;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	public void inti() {

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

		Provider Provider = new Provider();
		Provider.setProviderId(1);
		Provider.setProviderName("test");

		List<ProviderFoodItemsDto> listofFoodItem = new ArrayList<ProviderFoodItemsDto>();
		ProviderFoodItemsDto providerFoodItemsDto = new ProviderFoodItemsDto();
		providerFoodItemsDto.setItemId(5);
		providerFoodItemsDto.setName("chapati");
		providerFoodItemsDto.setPrice(40);
		providerFoodItemsDto.setProviderId(1);
		listofFoodItem.add(providerFoodItemsDto);
		when(foodproviderClient.getFoodItemsByname(Matchers.any(Integer.class))).thenReturn(Optional.of(Provider));
		when(foodproviderClient.getFoodItemsByproviderId(Matchers.any(Integer.class)))
				.thenReturn(Optional.of(listofFoodItem));

		when(foodproviderClient.getFoodItemsByname(Matchers.any(String.class))).thenReturn(Optional.of(listofFoodItem));
		when(bankServiceClient.validateAcc(Matchers.any(Integer.class))).thenReturn("valid");

		when(bankServiceClient.transafer(Matchers.any(TransactionDto.class))).thenReturn("Transafer");

		User user = new User();
		user.setUserId(1);
		user.setFirstName("Rk");
		user.setLastName("Manju");
		when(userRepository.findById(Matchers.any(Integer.class))).thenReturn(Optional.of(user));

	}

	@Test
	public void testPlaceorder() {

		OrderDto od = new OrderDto();

		od.setAccountNumber("500014");
		od.setProviderId(1);
		od.setUserId(1);
		FoodItemDtls foodItemDtls = new FoodItemDtls();
		foodItemDtls.setItemId(5);
		foodItemDtls.setQuantity(1);
		List<FoodItemDtls> listfooddtls = new ArrayList<FoodItemDtls>();
		listfooddtls.add(foodItemDtls);

		od.setFoodItemDtls(listfooddtls);

		ResponseDto rs = placeOrderServiceImpl.placeOrder(od);

		assertEquals("suceess", rs.getStatus());

	}

	@Test
	public void testgetFoodItemsByname() {

		OrderDto od = new OrderDto();

		List<ProviderFoodItemsDto> list = placeOrderServiceImpl.getFoodItemsByName("cha");

		assertEquals(list.size(), 1);

	}
}
