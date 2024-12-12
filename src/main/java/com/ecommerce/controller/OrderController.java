package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.model.Order;
import com.ecommerce.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order)
	{
		Order addOrder=this.orderService.addOrder(order);
		return new ResponseEntity<Order>(addOrder,HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<OrderDto>> getAllOrder(){
		
		List<OrderDto> getAll=this.orderService.getAllOrder();
		return new ResponseEntity<List<OrderDto>>(getAll,HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
	    OrderDto order = this.orderService.getOrderById(id);
	    return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable Long id)
	{
		this.orderService.deleteOrder(id);
		return new ResponseEntity<String>("delete successFully",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order)
	{
		Order updateOrder= this.orderService.addOrder(order);
		return new ResponseEntity<Order>(updateOrder,HttpStatus.OK);
	}
	

}
