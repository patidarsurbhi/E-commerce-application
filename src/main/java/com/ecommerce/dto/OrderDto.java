package com.ecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDto {
	    private Long id;
	    private Double totalAmount;
	    private String status;
	    private List<OrderItemDTO> orderItems;
}
