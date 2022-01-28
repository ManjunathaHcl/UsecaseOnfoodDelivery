package com.homelyfoodproviders.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.homelyfoodproviders.entity.Provider;
import com.homelyfoodproviders.repository.ProviderRepository;


@Component("providerWriter")
public class Writer implements ItemWriter<Provider>{
	
	@Autowired
	private ProviderRepository providerRepository;

	@Override
	@Transactional
	public void write(List<? extends Provider> provider) throws Exception {
		providerRepository.saveAll(provider);
	}
	
}
