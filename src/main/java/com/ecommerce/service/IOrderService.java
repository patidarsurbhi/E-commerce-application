package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.model.Order;

public interface IOrderService {

	
	public Order addOrder(Order order);
	
	public List<OrderDto> getAllOrder();
	
	public String deleteOrder(Long id);
	
	public Order updateOrder(Order order);
	
	public OrderDto getOrderById(Long id);
}
