package com.dambroski.foodDeliveryProject.restaurant;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.dambroski.foodDeliveryProject.Address.Address;
import com.dambroski.foodDeliveryProject.Address.AddressRepository;
import com.dambroski.foodDeliveryProject.delivery.Delivery;
import com.dambroski.foodDeliveryProject.delivery.DeliveryRepository;
import com.dambroski.foodDeliveryProject.delivery.DeliveryStatus;
import com.dambroski.foodDeliveryProject.error.DeliveryNotFoundException;
import com.dambroski.foodDeliveryProject.error.RestaurantNotFoundException;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	RestaurantRepository repository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	DeliveryRepository deliveryRepository;	

	@Override
	public List<Restaurant> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Restaurant post(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return repository.save(restaurant);
	}

	@Override
	public void deleteById(Long restaurantId) {
		Optional<Restaurant> optionalRestaurant = repository.findById(restaurantId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException("restaurant Not Found");
		}
		
		repository.deleteById(restaurantId);
		
	}
	
	

	@Override
	public Restaurant putRestaurant(Restaurant restaurant, Long restaurantId) {
		Optional<Restaurant> optionalRestaurant = repository.findById(restaurantId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant Not Found");
		}
		
		Restaurant newRestaurant = optionalRestaurant.get();
		
		if(Objects.nonNull(restaurant.getName()) && !"".equals(restaurant.getName())) {
			newRestaurant.setName(restaurant.getName());
		}
		if(Objects.nonNull(restaurant.getDescription()) && !"".equals(restaurant.getDescription())) {
			newRestaurant.setDescription(restaurant.getDescription());
		}
		
	
		repository.save(newRestaurant);
		return newRestaurant;
	}

	@Override
	public Restaurant addAddress(Address address, Long restaurantId) {
		Optional<Restaurant> optionalRestaurant = repository.findById(restaurantId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException();
		}
		Restaurant restaurant = optionalRestaurant.get();
		address.setType("RESTAURANT");
		address.setTypeId(restaurantId);
		addressRepository.save(address);
		restaurant.setAddress(address);
		return repository.save(restaurant);
	}
	
	@Override
	public Restaurant editAddress(Address address , Long restaurantId) {
		Optional<Restaurant> optionalRestaurant = repository.findById(restaurantId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant not foun");
		}
		Restaurant restaurant = optionalRestaurant.get();
		if(Objects.nonNull(address.getCity()) && !"".equals(address.getCity())) {
			restaurant.getAddress().setCity(address.getCity());
		}
		if(Objects.nonNull(address.getState()) && !"".equals(address.getState())) {
			restaurant.getAddress().setState(address.getState());
		}
		if(Objects.nonNull(address.getAddress()) && !"".equals(address.getAddress())) {
			restaurant.getAddress().setAddress(address.getAddress());
		}
		repository.save(restaurant);
		
		
		
		return restaurant;
	}


	@Override
	public Restaurant getById(Long restaurantId) {
		// TODO Auto-generated method stub
		return repository.findById(restaurantId).get();
	}

	@Override
	public Delivery aproveDelivery(Long deliveryId, Long restarauntId) {
		Optional<Restaurant> optionalRestaurant = repository.findById(restarauntId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException();
		}
		Restaurant restaurant = optionalRestaurant.get();
		Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
		if(optionalDelivery.isEmpty()) {
			throw new DeliveryNotFoundException("Delivery Not Found");
		}
		Delivery delivery = optionalDelivery.get();
		if(delivery.getOrder().getRestaurant() == restaurant) {
			delivery.setStatus(DeliveryStatus.COOKING);
			deliveryRepository.save(delivery);
		}else {
			throw new BadCredentialsException("Restaurant or delivery code incorrect");
		}
		
		return delivery;
	}

	@Override
	public Delivery inDelivery(Long restaurantId, Long deliveryId) {
		Optional<Restaurant> optionalRestaurant = repository.findById(restaurantId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException();
		}
		Restaurant restaurant = optionalRestaurant.get();
		Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
		if(optionalDelivery.isEmpty()) {
			throw new DeliveryNotFoundException("Delivery Not Found");
		}
		Delivery delivery = optionalDelivery.get();
		if(delivery.getOrder().getRestaurant() == restaurant) {
			delivery.setStatus(DeliveryStatus.IN_DELIVERY);
			deliveryRepository.save(delivery);
		}else {
			throw new BadCredentialsException("Restaurant or delivery code incorrect");
		}
		
		return delivery;
	}


	

}
