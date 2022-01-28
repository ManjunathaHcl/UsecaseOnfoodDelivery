package com.homelyfoodproviders.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homelyfoodproviders.dto.ProviderDto;
import com.homelyfoodproviders.dto.ProviderFoodItemsDto;
import com.homelyfoodproviders.entity.Provider;
import com.homelyfoodproviders.entity.ProviderMenu;
import com.homelyfoodproviders.service.ProviderService;

@RestController
public class ProviderController {

	@Autowired
	ProviderService providerServiceImpl;
	
    @Autowired
    JobLauncher jobLauncher;
    
    @Autowired
    @Qualifier("providerJob")
    Job providerJob;
    
    @Autowired
    @Qualifier("providerMenuJob")
    Job providerMenuJob;
	
	@GetMapping("/loadProviders")
	public List<ProviderDto> loadProviderData() throws Exception {
		
		JobParameters  jobParameter=  new JobParametersBuilder().addDate("date", new Date())
				.addString("source", "Spring Boot")
				.toJobParameters();
		jobLauncher.run(providerJob, jobParameter);
		
		return null;
	}
	@GetMapping("/loadProvider-menu")
	public List<ProviderFoodItemsDto> loadProviderMenu() throws Exception {
		
		JobParameters  jobParameter=  new JobParametersBuilder().addDate("date", new Date())
				.addString("source", "Spring Boot")
				.toJobParameters();

		jobLauncher.run(providerMenuJob, jobParameter);
		
		return null;
	}

	
	@GetMapping("/items/{foodItemName}")	
	public Optional<List<ProviderMenu>> getFoodItemsByname(@PathVariable String foodItemName){
		
		return providerServiceImpl.getFoodItemsByname(foodItemName);
	} 
	
	@GetMapping("provider/items/{providerId}")	
	public Optional<List<ProviderMenu>> getFoodItemsByproviderId(@PathVariable Integer providerId){
		
		return providerServiceImpl.getFoodItemsByproviderId(providerId);
	} 
	
	@GetMapping("/provider/{providerId}")	
	public Optional<Provider> getFoodItemsByname(@PathVariable Integer providerId){
		
		return providerServiceImpl.getProviderById(providerId);
	} 
	
}
