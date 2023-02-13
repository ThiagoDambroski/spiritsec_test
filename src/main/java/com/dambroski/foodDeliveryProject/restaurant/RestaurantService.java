package com.dambroski.foodDeliveryProject.restaurant;

import java.util.List;

import com.dambroski.foodDeliveryProject.Address.Address;
import com.dambroski.foodDeliveryProject.delivery.Delivery;

public interface RestaurantService {

	List<Restaurant> getAll();

	Restaurant post(Restaurant restaurant);

	void deleteById(Long restaurantId);

	Restaurant putRestaurant(Restaurant restaurant, Long restaurantId);

	Restaurant addAddress(Address address, Long restaurantId);

	Restaurant getById(Long restaurantId);

	Delivery aproveDelivery(Long deliveryId, Long restarauntId);

	Restaurant editAddress(Address address, Long addressId);

	Delivery inDelivery(Long restaurantId, Long deliveryId);

}
