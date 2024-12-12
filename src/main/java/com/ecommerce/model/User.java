package com.ecommerce.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''")
	@NotBlank(message = "User name is required")
	private String userName;
	
	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''")
	 @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
	private String Password;
	
	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''")
	@NotBlank(message = "Email is required")
	//@Email(message = "Invalid email format")
	private String email;
	
	@Column(columnDefinition = "VARCHAR(100) DEFAULT ''")
	@NotBlank(message = "Address is required")
	private String address;
	
	@OneToMany(mappedBy = "user")
	//@JsonIgnoreProperties(value={"user"})
	private List<Order> orders;
	 
    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER,mappedBy = "user")
    @JsonIgnoreProperties(value={"user"})
    private Set<UserRole> userRole=new HashSet<>();
}
