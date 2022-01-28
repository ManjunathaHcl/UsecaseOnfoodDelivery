package com.uks.bankservice.util;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uks.bankservice.entity.Account;
import com.uks.bankservice.entity.Customer;
import com.uks.bankservice.repository.AccountRepository;

@Component
public class BankServiceUtils {

	@Autowired
	private AccountRepository accountRepository;

	public Long generateAccountNumber(Long Id) {

		return Long.valueOf("5000" + Id);

	}

	public Account generateAccountDetails(Customer customer) {

		Account account = new Account();
		account.setAccountNumber(generateAccountNumber(customer.getId()));
		account.setAccountHolderName(customer.getFirstName());
		account.setAccoutCreationDate(LocalDate.now());
		account.setCurrentBalence(10000);

		return account;

	}

	public Boolean isValidAccount(Long Accountnumer) {
		Optional<Account> account = accountRepository.findById(Accountnumer);

		if (account.isPresent()) {
			return true;
		} else {
			return false;
		}

	}

}
