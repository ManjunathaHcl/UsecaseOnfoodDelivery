package com.homelyfoodproviders.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homelyfoodproviders.dto.ProviderFoodItemsDto;
import com.homelyfoodproviders.entity.Provider;
import com.homelyfoodproviders.entity.ProviderMenu;
import com.homelyfoodproviders.repository.ProviderMenuRepository;
import com.homelyfoodproviders.repository.ProviderRepository;


@Service
public class ProviderServiceImpl implements ProviderService {

	
	@Autowired 
	private ProviderMenuRepository providerMenuRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Override
	public Optional<List<ProviderMenu>> getFoodItemsByname(String foodItemName) {
		
		return providerMenuRepository.findByNameContainingIgnoreCase(foodItemName);
	
	}
	
	
	@Override
	public Optional<Provider> getProviderById(Integer providerId) {
		// TODO Auto-generated method stub
		return providerRepository.findById(providerId);
	}
	
	
	@Override
	public Optional<List<ProviderMenu>> getFoodItemsByproviderId(Integer providerId) {
		// TODO Auto-generated method stub
		return providerMenuRepository.findByProviderId(providerId);
	}
}
