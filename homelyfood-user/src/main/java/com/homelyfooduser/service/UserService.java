package com.homelyfooduser.service;

import com.homelyfooduser.dto.UserDto;
import com.homelyfooduser.entity.User;

public interface UserService {
	
	User addUser(UserDto  userDto);

	User getUser(Integer userId );

	
}
