package com.homelyfoodproviders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homelyfoodproviders.dto.ProviderFoodItemsDto;
import com.homelyfoodproviders.entity.ProviderMenu;

@Repository
public interface ProviderMenuRepository extends JpaRepository<ProviderMenu,  Integer> {

	Optional<List<ProviderMenu>> findByNameContainingIgnoreCase(String Name);
	
	Optional<List<ProviderMenu>> findByProviderId(Integer ProviderId);
	
}
