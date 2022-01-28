package com.homelyfoodproviders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.homelyfoodproviders.dto.ProviderFoodItemsDto;
import com.homelyfoodproviders.entity.Provider;
import com.homelyfoodproviders.entity.ProviderMenu;

public interface ProviderService {

	Optional<List<ProviderMenu>> getFoodItemsByname(String foodItemName);
	Optional<List<ProviderMenu>> getFoodItemsByproviderId(Integer providerId);
	Optional<Provider> getProviderById(Integer providerId);

}
