package com.uks.bankservice.service;

import java.util.List;

import com.uks.bankservice.dto.AccountDto;
import com.uks.bankservice.dto.CustomerDto;
import com.uks.bankservice.dto.TransactionDto;
import com.uks.bankservice.entity.Transaction;

public interface BankService {

	public AccountDto registerCustomer(CustomerDto customerDto);

	public String createTransaction(TransactionDto transactionDto);

	public List<Transaction> generateXtnReport(Long accountNumber, Integer month, Integer year);

	public String validAccNo(Integer accno);
}
