package com.homelyfooduser.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.homelyfooduser.dto.TransactionDto;



@FeignClient("http://bankservice/api/customer")
public interface BankServiceClient {
	
	@PostMapping("/transfer" )
	public String transafer(@RequestBody TransactionDto transactionDto);
	
	
	@GetMapping("/validate/{accno}")
	public String validateAcc(@PathVariable Integer accno);

}