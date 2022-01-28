package com.uks.bankservice.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	private Long accountNumber;
	
	private String accountHolderName;
	
	private LocalDate AccoutCreationDate;
	
	private Integer currentBalence;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public LocalDate getAccoutCreationDate() {
		return AccoutCreationDate;
	}

	public void setAccoutCreationDate(LocalDate accoutCreationDate) {
		AccoutCreationDate = accoutCreationDate;
	}

	public int getCurrentBalence() {
		return currentBalence;
	}

	public void setCurrentBalence(Integer currentBalence) {
		this.currentBalence = currentBalence;
	}
	
		

}
