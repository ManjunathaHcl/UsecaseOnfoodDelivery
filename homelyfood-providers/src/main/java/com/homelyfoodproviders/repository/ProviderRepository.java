package com.homelyfoodproviders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homelyfoodproviders.dto.ProviderFoodItemsDto;
import com.homelyfoodproviders.entity.Provider;

@Repository
public interface ProviderRepository  extends JpaRepository<Provider, Integer>{
	


}
