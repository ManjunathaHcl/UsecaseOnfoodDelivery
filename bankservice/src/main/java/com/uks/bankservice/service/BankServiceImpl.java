package com.uks.bankservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uks.bankservice.dto.AccountDto;
import com.uks.bankservice.dto.CustomerDto;
import com.uks.bankservice.dto.TransactionDto;
import com.uks.bankservice.entity.Account;
import com.uks.bankservice.entity.Customer;
import com.uks.bankservice.entity.Transaction;
import com.uks.bankservice.exception.InvalidAccountNumber;
import com.uks.bankservice.repository.AccountRepository;
import com.uks.bankservice.repository.CustomerRepository;
import com.uks.bankservice.repository.TransactionRepository;
import com.uks.bankservice.util.BankServiceUtils;

import net.bytebuddy.implementation.bytecode.Throw;

@Service(value = "bankServiceImpl")
public class BankServiceImpl implements BankService {

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankServiceUtils bankServiceUtils;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public AccountDto registerCustomer(CustomerDto customerDto) {

		Customer customer = modelMapper.map(customerDto, Customer.class);
		// customer.setPhoneNumber(Long.parseLong(customerDto.getPhoneNumber()));

		customer = customerRepository.save(customer);

		Account account = bankServiceUtils.generateAccountDetails(customer);

		account = accountRepository.save(account);

		AccountDto accountDto = modelMapper.map(account, AccountDto.class);
		accountDto.setMessage("Customer has been register with us,check the account details");

		return accountDto;
	}

	@Override
	public String createTransaction(TransactionDto transactionDto) {
		// TODO Auto-generated method stub
		Transaction CreditTransaction = modelMapper.map(transactionDto, Transaction.class);
		Transaction DebitTransaction = modelMapper.map(transactionDto, Transaction.class);
		if (bankServiceUtils.isValidAccount(transactionDto.getFromAccount())
				& bankServiceUtils.isValidAccount(transactionDto.getToAccount())) {

			CreditTransaction.setTransactionDate(LocalDateTime.now());
			CreditTransaction.setAccountNumber(transactionDto.getToAccount());
			CreditTransaction.setToAccount(0L);
			CreditTransaction.setTransactionType("Credit");
			transactionRepository.saveAndFlush(CreditTransaction);

			DebitTransaction.setTransactionDate(LocalDateTime.now());
			DebitTransaction.setAccountNumber(transactionDto.getFromAccount());
			DebitTransaction.setFromAccount(0L);
			DebitTransaction.setTransactionType("Debit");
			transactionRepository.saveAndFlush(DebitTransaction);

			makeMoneyMoment(transactionDto.getFromAccount(), transactionDto.getAmount(), "Debit");
			makeMoneyMoment(transactionDto.getToAccount(), transactionDto.getAmount(), "Credit");

			return "Transaction has been created";

		} else {
			throw new InvalidAccountNumber();
		}

	}

	/*
	 * This method is for generating the reports on monthly basis
	 * 
	 */
	@Override
	public List<Transaction> generateXtnReport(Long accountNumber, Integer month, Integer year) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd H:mm");

		LocalDateTime localDateTime = LocalDateTime.parse(year + "-" + month + "-" + "01 00:00", formatter);

		LocalDateTime firstDayOfMonth = localDateTime;
		LocalDateTime lastDayOfMonth = localDateTime.plusMonths(1).minusDays(1);

		return transactionRepository.findByAccountNumberAndTransactionDateBetween(accountNumber, firstDayOfMonth,
				lastDayOfMonth);
	}
	
	public String validAccNo(Integer accno) {
		
		Optional<Account> ac=accountRepository.findById(accno.longValue());
		
		if (ac.isPresent()) {
			return "valid";
		} else {
			return "Invalid";

		}
	}

	/*
	 * This method is for doing money moment between accounts
	 * 
	 * @param AccountNumber
	 * 
	 * @param amount
	 * 
	 * @param xtnType -credit or debit
	 * 
	 */
	public void makeMoneyMoment(Long AccountNumber, int amount, String xtnType) {

		Account account = accountRepository.getById(AccountNumber);
		if (xtnType.equals("Debit")) {
			account.setCurrentBalence(account.getCurrentBalence() - amount);
		} else {
			account.setCurrentBalence(account.getCurrentBalence() + amount);
		}
		accountRepository.saveAndFlush(account);

	}

}
