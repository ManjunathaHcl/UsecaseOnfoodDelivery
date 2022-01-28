package com.uks.bankservice.dto;

import java.time.LocalDate;

public class AccountDto {

private Long accountNumber;
	
	private String accountHolderName;
	
	private LocalDate AccoutCreationDate;
	
	private int currentBalence;
	
	private String message;

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

	public void setCurrentBalence(int currentBalence) {
		this.currentBalence = currentBalence;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
