package com.homelyfooduser.external;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.homelyfooduser.entity.Provider;
import com.homelyfooduser.dto.ProviderFoodItemsDto;

@FeignClient("http://homelyfood-providers/homelyfood-provider")
public interface FoodProviderClient {

	@GetMapping("/items/{foodItemName}")
	public Optional<List<ProviderFoodItemsDto>> getFoodItemsByname(@PathVariable String foodItemName);

	@GetMapping("/provider/{providerId}")
	public Optional<Provider> getFoodItemsByname(@PathVariable Integer providerId);

	@GetMapping("/provider/items/{providerId}")
	public Optional<List<ProviderFoodItemsDto>> getFoodItemsByproviderId(@PathVariable Integer providerId);
}