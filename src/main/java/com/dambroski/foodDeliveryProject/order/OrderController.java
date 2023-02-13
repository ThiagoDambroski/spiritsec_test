package com.dambroski.foodDeliveryProject.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Delegate;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@GetMapping("/getAll")
	public List<Order> getAll(){
		return service.getAll();
	}
	
	@PostMapping("/post/{userId}/{addressId}/{restaurantId}")
	public Order postOrder(@RequestBody Order order,@PathVariable("userId") Long userId,@PathVariable("addressId")Long addressId,
			@PathVariable("restaurantId") Long restaurantId)
			throws Exception {
		return service.post(order,userId,addressId,restaurantId);
	}
	
	
	@DeleteMapping("/delete/{orderId}")
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		service.deleteById(orderId);
	}
	
	@PutMapping("/paid/{orderId}")
	public Order paidOrder(@PathVariable("orderId")Long orderId) {
		return service.paidOrder(orderId);
	}
	
	
}
