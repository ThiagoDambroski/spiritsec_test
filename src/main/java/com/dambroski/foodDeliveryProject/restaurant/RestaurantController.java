package com.dambroski.foodDeliveryProject.restaurant;

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

import com.dambroski.foodDeliveryProject.Address.Address;
import com.dambroski.foodDeliveryProject.delivery.Delivery;

import lombok.Delegate;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	RestaurantService service;
	
	@GetMapping("/getAll")
	public List<Restaurant> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/getById/{restaurantId}")
	public Restaurant getById(@PathVariable(name = "restaurantId") Long restaurantId) {
		return service.getById(restaurantId);
	}
	
	@PostMapping("/post")
	public Restaurant postRestaurant(@RequestBody Restaurant restaurant){
		return service.post(restaurant);
	}
	
	@PutMapping("/put/{restaurantId}")
	public Restaurant putRestaurant(@RequestBody Restaurant restaurant,@PathVariable("restaurantId") Long restaurantId ) {
		return service.putRestaurant(restaurant,restaurantId);
	}
	
	@DeleteMapping("/delete/{restaurantId}")
	public void deleteResturantById(@PathVariable("restaurantId")Long restaurantId) {
		 service.deleteById(restaurantId);
	}
	
	@PutMapping("/addAddress/{restaurantId}")
	public Restaurant addAddress(@RequestBody Address address,@PathVariable("restaurantId") Long restaurantId ) {
		return service.addAddress(address,restaurantId);
	}
	
	@PutMapping("/edit/address/{restaurantId}/{addressId}")
	public Restaurant editAddress(@RequestBody Address address ,@PathVariable("restaurantId") Long restaurantId) {
		return service.editAddress(address,restaurantId);
	}
	
	@PutMapping("/aproveDelivery/{restaurantId}/{deliveryId}")
	public Delivery aproveDelivery(@PathVariable("deliveryId") Long deliveryId
			,@PathVariable("restaurantId") Long restarauntId) {
		return service.aproveDelivery(deliveryId,restarauntId);
	}
	
	@PutMapping("/inDelivery/{restaurantId}/{deliveryId}")
	public Delivery inDelivery(@PathVariable("restaurantId") Long restaurantId,@PathVariable("deliveryId") Long deliveryId) {
		return service.inDelivery(restaurantId,deliveryId);
	}
}
