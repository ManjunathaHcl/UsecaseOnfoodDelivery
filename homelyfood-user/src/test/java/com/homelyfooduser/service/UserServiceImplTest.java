package com.homelyfooduser.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.homelyfooduser.dto.UserDto;
import com.homelyfooduser.entity.User;
import com.homelyfooduser.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void init() {

		User user = new User();
		user.setUserId(1);
		user.setFirstName("Manju");
		user.setLastName("RK");
		when(userRepository.findById(Matchers.any(Integer.class))).thenReturn(Optional.of(user));
		when(userRepository.save(Matchers.any(User.class))).thenReturn(user);

	}

	@Test
	public void testSaveUser() {
		UserDto user = new UserDto();
		user.setFirstName("Manju");
		user.setLastName("RK");
		User userentity = userServiceImpl.addUser(user);
		assertEquals(userentity.getUserId(), 1);

	}

	@Test
	public void testGetUser() {

		User userentity = userServiceImpl.getUser(1);
		assertEquals(userentity.getUserId(), 1);

	}

}
