package com.uks.bankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.uks.bankservice.entity.Account;
@Component
public interface AccountRepository  extends JpaRepository<Account, Long>{
	
	
	

}
