package com.dambroski.foodDeliveryProject.food;

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

@RestController
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	FoodService service;
	
	
	@GetMapping("/getAll")
	public List<Food> getAllFoods(){
		return service.getAllFoods();
	}
	
	@GetMapping("/getByName/{name}")
	public List<Food> getFoodByName(@PathVariable("name") String name){
		return service.getFoodByName(name);
	}
	
	@GetMapping("/getByCategory/{category}")
	public List<Food> getByCategory(@PathVariable("category") String category){
		return service.getFoodByCategory(category);
	}
	
	@PostMapping("/post/{restaurantId}")
	public Food postFood(@RequestBody Food food,@PathVariable(name = "restaurantId")Long restaurantId) {
		return service.postFood(food,restaurantId);
	}
	
	
	
	@PutMapping("/put/{foodId}")
	public Food putFood(@RequestBody Food food, @PathVariable(name = "foodId") Long id) {
		return service.putFood(food,id);
	}
	
	
	
	@DeleteMapping("/delete/{foodId}")
	public void deleteById(@PathVariable("foodId") Long id) {
		service.deleteById(id);
	}
	
	

}
