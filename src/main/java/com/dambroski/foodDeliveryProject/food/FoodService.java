package com.dambroski.foodDeliveryProject.food;

import java.util.List;

public interface FoodService {

	List<Food> getAllFoods();

	Food postFood(Food food,Long restaurantId);

	Food putFood(Food food, Long id);

	void deleteById(Long id);

	List<Food> getFoodByName(String name);

	List<Food> getFoodByCategory(String category);


}
