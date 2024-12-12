package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderItemDTO {

	   private Long id;
	    private Long productId; // Instead of the full Product object
	    private Integer quantity;
	    private Double price;
}
