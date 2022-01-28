package com.homelyfooduser.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.homelyfooduser.dto.FoodItemDtls;
import com.homelyfooduser.dto.OrderDto;
import com.homelyfooduser.dto.ProviderFoodItemsDto;
import com.homelyfooduser.dto.ResponseDto;
import com.homelyfooduser.dto.TransactionDto;
import com.homelyfooduser.entity.OrderItems;
import com.homelyfooduser.entity.Orders;
import com.homelyfooduser.entity.Provider;
import com.homelyfooduser.entity.ProviderMenu;
import com.homelyfooduser.entity.User;
import com.homelyfooduser.exception.InvalidDataException;
import com.homelyfooduser.external.BankServiceClient;
import com.homelyfooduser.external.FoodProviderClient;
import com.homelyfooduser.repository.OrderItemsRepository;
import com.homelyfooduser.repository.OrderRepository;
import com.homelyfooduser.repository.UserRepository;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

	@Autowired
	FoodProviderClient foodProviderClient;

	@Autowired
	UserRepository userRepository;
	@Autowired
	BankServiceClient bankServiceClient;

	@Autowired
	private OrderItemsRepository orderItemsRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<ProviderFoodItemsDto> getFoodItemsByName(String foodName) {

		Optional<List<ProviderFoodItemsDto>> foodItems = foodProviderClient.getFoodItemsByname(foodName);

		if (foodItems.isPresent()) {
			return foodItems.get();

		} else {
			throw new InvalidDataException("Sorry we dont have food item you require");
		}

	}

	@Override
	@Transactional
	public ResponseDto placeOrder(OrderDto orderDto) {

		String orderId = "Order" + "-" + LocalDateTime.now().getHour() + "-" + LocalDateTime.now().getSecond();
		// TODO Auto-generated method stub

		// validate userId
		Optional<User> user = userRepository.findById(orderDto.getUserId());
		if (!user.isPresent()) {

			throw new InvalidDataException("Please enter valid user id");

		}
		// validate accountNumber
		String validAcc = bankServiceClient.validateAcc(Integer.parseInt(orderDto.getAccountNumber()));

		if (!validAcc.equalsIgnoreCase("valid")) {

			throw new InvalidDataException("account Number is invalid,Please enter valid Account enter");

		}
		// validate providerId
		Optional<Provider> provider = foodProviderClient.getFoodItemsByname(orderDto.getProviderId());
		if (!provider.isPresent()) {
			throw new InvalidDataException("provider id is invalid,Please enter valid provider");

		}
		Optional<List<ProviderFoodItemsDto>> foodItems = foodProviderClient
				.getFoodItemsByproviderId(orderDto.getProviderId());

		if (!foodItems.isPresent()) {
			throw new InvalidDataException(
					"provider does not have any product,Please Choose another provider");
		}

		List<OrderItems> orderItems = poulateOrderItems(foodItems.get(), orderDto.getFoodItemDtls(), orderId);

		Orders order = new Orders();
		order.setOrderId(orderId);
		order.setProviderId(provider.get().getProviderId());
		order.setUserId(user.get().getUserId());
		order.setAccountNumber(Long.parseLong(orderDto.getAccountNumber()));
		order.setTotalOrderAmount(orderItems.stream().mapToInt((oi) -> oi.getTotalAmount()).sum());
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus("Success");

		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAmount(order.getTotalOrderAmount());
		transactionDto.setFromAccount(Long.parseLong(orderDto.getAccountNumber()));
		transactionDto.setToAccount(500014L);
		transactionDto.setComment("Homely Food orders");

		try {
			bankServiceClient.transafer(transactionDto);
		} catch (Exception e) {

			throw new InvalidDataException(e.getMessage());
		}

		order = orderRepository.save(order);
		orderItemsRepository.saveAll(orderItems);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus("suceess");
		responseDto.setMessage("Order has been created with Order id : " + orderId);
		return responseDto;

	}

	private List<OrderItems> poulateOrderItems(List<ProviderFoodItemsDto> providerFoodItemsDto,
			List<FoodItemDtls> foodItemDtls, String orderId) {
		// TODO Auto-generated method stub
		List<OrderItems> orderItems = new ArrayList<>();

		Iterator<FoodItemDtls> orderFoodItems = foodItemDtls.iterator();

		while (orderFoodItems.hasNext()) {
			FoodItemDtls foodItem = orderFoodItems.next();

			Optional<ProviderFoodItemsDto> orderedItemDetails = providerFoodItemsDto.stream()
					.filter((p) -> foodItem.getItemId() == p.getItemId()).findFirst();

			if (orderedItemDetails.isPresent()) {
				OrderItems orderItem = new OrderItems();
				orderItem.setItemId(foodItem.getItemId());
				orderItem.setOrderId(orderId);
				orderItem.setQuantity(foodItem.getQuantity());
				orderItem.setPrice(orderedItemDetails.get().getPrice());
				orderItem.setTotalAmount(orderedItemDetails.get().getPrice() * foodItem.getQuantity());
				orderItem.setName(orderedItemDetails.get().getName());
				orderItems.add(orderItem);

			} else {
				throw new InvalidDataException(
						"Entred food item id is not at given provider,please choose valid food item");
			}

		}

		return orderItems;
	}

}
