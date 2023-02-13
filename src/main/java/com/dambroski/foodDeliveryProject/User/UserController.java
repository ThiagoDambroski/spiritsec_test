package com.dambroski.foodDeliveryProject.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dambroski.foodDeliveryProject.Address.Address;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/getAll")
	public List<User> getAllUsers(){
		return service.getAll();
	}
	
	@GetMapping("/getById/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return service.getUserById(id);
	}
	
	@PostMapping("/post/admin")
	public User postUserAdmin(@RequestBody User user) {
		return service.postUserAdmin(user);
	}
	
	@PostMapping("/post/user")
	public User postUser(@RequestBody User user) {
		return service.postUser(user);
	}
	
	@PostMapping("/post/restaurant")
	public User postUserRestaurant(@RequestBody User user) {
		return service.postUserRestaurant(user);
	}
	
	@PostMapping("/post/delivery")
	public User postUserDelivery(@RequestBody User user) {
		return service.postUserDelivery(user);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
	}
	
	@PutMapping("/edit/{id}")
	public User editUser(@PathVariable("id")Long id, @RequestBody User user) {
		return service.editUser(user,id);
	}
	@PutMapping("/addAddress/{userId}")
	public User addAddress(@RequestBody Address address,@PathVariable("userId") Long userId) {
		return service.addAddress(address,userId);
	}
	@PutMapping("/editAddress/{userId}/{addressId}")
	public User editAddress(@RequestBody Address address,@PathVariable("userId") Long userId,
			@PathVariable("addressId") Long addressId) {
		
		return service.editAddress(address,userId,addressId);
	
		
	}

	

}
