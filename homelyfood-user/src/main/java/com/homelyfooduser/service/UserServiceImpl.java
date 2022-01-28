package com.homelyfooduser.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homelyfooduser.dto.UserDto;
import com.homelyfooduser.entity.User;
import com.homelyfooduser.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;



	@Override
	public User addUser(UserDto userDtoss) {
		// TODO Auto-generated method sreturn null;tub
		 User user= new User();
		 BeanUtils.copyProperties(userDtoss, user);
		 user = userRepository.save(user);
		 
		return user;
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub

		Optional<User> user = userRepository.findById(userId);

		return user.get();
	}

}
