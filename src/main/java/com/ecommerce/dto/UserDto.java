package com.ecommerce.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ecommerce.model.Order;
import com.ecommerce.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	
	private Long userId;
	
	private String userName;
	
	private String Password;
	
	private String email;
	
	
	private String address;
	

	private List<Order> orders;
	 
 
    private Set<UserRole> userRole=new HashSet<>();
}


