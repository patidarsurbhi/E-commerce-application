package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecommerce.exception.NotblankException;
import com.ecommerce.exception.ResourceAlreadyExistException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Roles;
import com.ecommerce.model.User;
import com.ecommerce.model.UserRole;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
	 
		 if (userRepository.existsByUserName(user.getUserName())) {
		        throw new ResourceAlreadyExistException("Username is already taken.");
		    }

		    // Check if the email already exists
		    if (userRepository.existsByEmail(user.getEmail())) {
		        throw new ResourceAlreadyExistException("Email is already taken.");
		    }

	    String encPwd=passwordEncoder.encode(user.getPassword());
 		user.setPassword(encPwd);
	 
	    for (UserRole userRole : userRoles) {
	        Roles role = userRole.getRole();
	        Roles existingRole = roleRepository.findById(role.getId()).orElse(null);
	        if (existingRole == null) {
	            roleRepository.save(role);
	        }
	    }
	    
	    // Assign roles to the user
	    user.getUserRole().addAll(userRoles);
	    
	    user = this.userRepository.save(user);
	    return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optional=userRepository.findByUserName(username);
		if(optional.isEmpty())
		{
			throw new UsernameNotFoundException(username +"NOT EXIST");
		}
		User user=optional.get();
		List<GrantedAuthority> authorities= user.getUserRole()
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getRole().getRoleName()))
				.collect(Collectors.toList());
		return  new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		 return userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
	}

	@Override
	public User updateUser(User user) {
		 Optional<User> existingUserGet = userRepository.findById(user.getUserId());
	        if (existingUserGet.isPresent()) {
	            
	            User existingUser = existingUserGet.get();
	            existingUser.setUserName(user.getUserName());
	            existingUser.setEmail(user.getEmail());
	            existingUser.setAddress(user.getAddress());
	            String encPwd=passwordEncoder.encode(user.getPassword());
	            existingUser.setPassword(encPwd);
	            // Save the updated product
	            return userRepository.save(existingUser);
	        } else {
	            throw new ResourceNotFoundException("Product not found with ID: " + user.getUserId());
	        }
	}
}
