package com.homelyfooduser.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.homelyfooduser.dto.UserDto;
import com.homelyfooduser.entity.User;
import com.homelyfooduser.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;


	@PostMapping("/register")
	@ApiOperation(value = "To register Customer with the homelyfood app")
	public User userRegistration(@Valid @RequestBody UserDto userDto) {
		return userServiceImpl.addUser(userDto);
		
	}
	
	@GetMapping("/user/{userId}")
	@ApiOperation(value = "To get the Customer ")
	public User getCustomer(@PathVariable Integer userId) {
		return userServiceImpl.getUser(userId);
		
	}
	
}
