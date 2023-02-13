package com.dambroski.foodDeliveryProject.food;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.foodDeliveryProject.restaurant.RestaurantRepository;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	FoodRepository repository;
	
	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public List<Food> getAllFoods() {
	
		return repository.findAll();
	}

	@Override
	public Food postFood(Food food,Long restaurantId) {
		food.setRestaurant(restaurantRepository.findById(restaurantId).get());
		return repository.save(food);
	}

	@Override
	public Food putFood(Food food, Long id) {
		Food newFood = repository.findById(id).get();
		if(Objects.nonNull(food.getName()) && !"".equals(food.getName())) {
			newFood.setName(food.getName());
		}
		if(Objects.nonNull(food.getDescription())) {
			newFood.setDescription(food.getDescription());
		}
		if(Objects.nonNull(food.getPrice())) {
			newFood.setPrice(food.getPrice());
		}
		if(Objects.nonNull(food.getCategory())) {
			newFood.setCategory(food.getCategory());
		}
		if(Objects.nonNull(food.getStock())) {
			newFood.setStock(food.getStock());
		}
		
		
		return repository.save(newFood);
		
		
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<Food> getFoodByName(String name) {
		// TODO Auto-generated method stub
		return repository.getFoodByName(name);
	}

	@Override
	public List<Food> getFoodByCategory(String category) {
		
		return repository.getFoodByCategory(category);
	}

	
}
