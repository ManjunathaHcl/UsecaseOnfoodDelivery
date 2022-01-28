package com.uks.bankservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uks.bankservice.dto.AccountDto;
import com.uks.bankservice.dto.CustomerDto;
import com.uks.bankservice.dto.TransactionDto;
import com.uks.bankservice.entity.Transaction;
import com.uks.bankservice.service.BankService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	@Qualifier(value = "bankServiceImpl")
	private BankService bankService;

	@PostMapping("/register")
	@ApiOperation(value = "To register Customer with the Bank")
	public AccountDto customerRegistration(@Valid @RequestBody CustomerDto customerDto) {
		return bankService.registerCustomer(customerDto);
	}
	
	
	
	
}
