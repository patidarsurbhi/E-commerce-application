package com.ecommerce.service;

import java.util.Set;

import com.ecommerce.model.User;
import com.ecommerce.model.UserRole;

public interface IUserService {
	
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
	public User getUserById(Long id);
	
	public User updateUser(User user);

}
