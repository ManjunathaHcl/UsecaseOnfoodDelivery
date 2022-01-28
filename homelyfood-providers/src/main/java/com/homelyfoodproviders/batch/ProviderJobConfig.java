package com.homelyfoodproviders.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.homelyfoodproviders.entity.Provider;
import com.homelyfoodproviders.entity.ProviderMenu;

@Component
public class ProviderJobConfig extends JobExecutionListenerSupport {

	@Autowired(required = true)
	StepBuilderFactory stepBuilderFactory;

	@Autowired
	JobBuilderFactory jobBuilderFactory;

	@Value("${provider.data.path}")
	Resource resource;
	
	@Value("${provider.menu.data.path}")
	Resource Menuresource;
	
	@Autowired
	@Qualifier("providerWriter")
	Writer writer;
	
	@Autowired
	@Qualifier("providerMenuWriter")
	ProviderMenuWriter providerMenuWriter;

	@Bean("providerJob")
	public Job providerJob() {

		Step step1 = stepBuilderFactory.get("Step-1").<Provider, Provider>chunk(2).reader(new Reader(resource))
				.writer(writer).build();

		Job job = jobBuilderFactory.get("providerJob").incrementer(new RunIdIncrementer()).listener(this)
				.start(step1).build();

		return job;

	}
	
	@Bean("providerMenuJob")
	public Job providerMenuJob() {

		Step step1 = stepBuilderFactory.get("Step-1").<ProviderMenu, ProviderMenu>chunk(2).reader(new providerMenuReader(Menuresource))
				.writer(providerMenuWriter).build();

		Job job = jobBuilderFactory.get("providerMenuJob").incrementer(new RunIdIncrementer()).listener(this)
				.start(step1).build();

		return job;

	}


}
