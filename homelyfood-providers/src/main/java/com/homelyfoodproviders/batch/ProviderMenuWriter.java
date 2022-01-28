package com.homelyfoodproviders.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.homelyfoodproviders.entity.ProviderMenu;
import com.homelyfoodproviders.repository.ProviderMenuRepository;


@Component
public class ProviderMenuWriter implements ItemWriter<ProviderMenu>{
	
	@Autowired
	private ProviderMenuRepository providerMenuRepository;

	@Override
	@Transactional
	public void write(List<? extends ProviderMenu> providerMenu) throws Exception {
		providerMenuRepository.saveAll(providerMenu);
	}
	
}
