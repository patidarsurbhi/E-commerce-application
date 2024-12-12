package com.ecommerce.request;

import java.util.List;

import javax.persistence.OneToMany;

import com.ecommerce.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

	private Long id;
    
    private String name;
    
    private String description;
    
    private Double price;
    
    private Integer stock;
    
    private List<OrderItem> orderItems;
	
}
