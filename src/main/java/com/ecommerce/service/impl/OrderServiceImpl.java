package com.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.constants.AppConstant;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.OrderItemDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	public Order addOrder(Order order) {
	    // Set the order reference in each OrderItem to ensure proper mapping
	    if (order.getOrderItems() != null) {
	        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));
	    }
	    LocalDateTime now = LocalDateTime.now();
	    order.setCreatedAt(now);    
	    order.setUpdatedAt(now);  
	    return this.orderRepository.save(order);
	}


	
	@Override
	public String deleteOrder(Long id) {
		// TODO Auto-generated method stub
		this.orderRepository.deleteById(id);
		return AppConstant.ORDER_DELETED_SUCCESS_FULLY;
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		 Optional<Order> existingOrderOpt = orderRepository.findById(order.getId());
	        if (existingOrderOpt.isPresent()) {

	            Order existingOrder = existingOrderOpt.get();
	            existingOrder.setTotalAmount(order.getTotalAmount());
	            // Save the updated product
	            return orderRepository.save(existingOrder);
	        } else {
	            throw new ResourceNotFoundException(AppConstant.ORDER_NOT_FOUND_THIS_ID + order.getId());
	        }
	        
	}

	



	@Override
	public List<OrderDto> getAllOrder() {
	    List<Order> orders = this.orderRepository.findAll();
	    
	    return orders.stream().map(order -> {
	        OrderDto dto = new OrderDto();
	        dto.setId(order.getId());
	        dto.setTotalAmount(order.getTotalAmount());
	        dto.setStatus(order.getStatus());

	        List<OrderItemDTO> items = order.getOrderItems().stream().map(item -> {
	            OrderItemDTO itemDTO = new OrderItemDTO();
	            itemDTO.setId(item.getId());
	            itemDTO.setProductId(item.getProduct().getId()); // Assuming you only want the product ID
	            itemDTO.setQuantity(item.getQuantity());
	            itemDTO.setPrice(item.getPrice());
	            return itemDTO;
	        }).collect(Collectors.toList());

	        dto.setOrderItems(items);
	        return dto;
	    }).collect(Collectors.toList());
	}



	@Override
	public OrderDto getOrderById(Long id) {
		 Order order = orderRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException(AppConstant.ORDER_NOT_FOUND_THIS_ID + id));

		    // Map Order to OrderDto
		    OrderDto orderDto = new OrderDto();
		    orderDto.setId(order.getId());
		    orderDto.setTotalAmount(order.getTotalAmount());
		    orderDto.setStatus(order.getStatus());

		    // Map OrderItems to OrderItemDtos
		    List<OrderItemDTO> orderItemDtos = order.getOrderItems().stream()
		            .map(item -> {
		                OrderItemDTO itemDto = new OrderItemDTO();
		                itemDto.setId(item.getId());
		                itemDto.setProductId(item.getProduct().getId()); // Assuming you just need the productId
		                itemDto.setQuantity(item.getQuantity());
		                itemDto.setPrice(item.getPrice());
		                return itemDto;
		            })
		            .collect(Collectors.toList());

		    orderDto.setOrderItems(orderItemDtos);
		    return orderDto;
	}

	
	
	

}
