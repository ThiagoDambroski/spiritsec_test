package com.dambroski.foodDeliveryProject.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dambroski.foodDeliveryProject.Address.Address;
import com.dambroski.foodDeliveryProject.Address.AddressRepository;
import com.dambroski.foodDeliveryProject.User.User;
import com.dambroski.foodDeliveryProject.User.UserRepository;
import com.dambroski.foodDeliveryProject.delivery.Delivery;
import com.dambroski.foodDeliveryProject.delivery.DeliveryRepository;
import com.dambroski.foodDeliveryProject.delivery.DeliveryStatus;
import com.dambroski.foodDeliveryProject.error.NotEnoughFoodException;
import com.dambroski.foodDeliveryProject.error.RestaurantDontDeliveryException;
import com.dambroski.foodDeliveryProject.error.RestaurantNotFoundException;
import com.dambroski.foodDeliveryProject.food.Food;
import com.dambroski.foodDeliveryProject.food.FoodRepository;
import com.dambroski.foodDeliveryProject.orderFood.OrderFood;
import com.dambroski.foodDeliveryProject.orderFood.OrderFoodRepository;
import com.dambroski.foodDeliveryProject.restaurant.Restaurant;
import com.dambroski.foodDeliveryProject.restaurant.RestaurantRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository repository;
	
	@Autowired
	FoodRepository foodRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	OrderFoodRepository orderFoodRepository;
	
	@Autowired
	DeliveryRepository deliveryRepository;
	
	@Autowired
	AddressRepository addressRepository;
	

	@Override
	public List<Order> getAll() {
		return repository.findAll();
	}

	@Override
	public Order post(Order order,Long userId,Long addressId,Long restaurantId) throws Exception {
		List<Long> listIds = order.getFoodsId();
		List<OrderFood> list = new ArrayList<>();
		if(listIds == null) {
			throw new Exception("getFoodsIds is empty");
		}
		if(order.getFoods() != null) {
			list = order.getFoods();
		}
		
		double sum = 0;
		
		for (Long id : listIds) {
			
			list.add(orderFoodRepository.findById(id).get());
			
		}	
		
		
		for (OrderFood orderFood : list) {
			Food food = orderFood.getFood();
			if(food.getStock() >= orderFood.getQuantity()) {
				food.setStock(food.getStock() - orderFood.getQuantity());
				foodRepository.save(food);
				sum += orderFood.getQuantity() * food.getPrice();
			}else {
				throw new NotEnoughFoodException(String.format("The %s dosent have %i itens in stock,only %i"
						, food.getName(),orderFood.getQuantity(),food.getStock()));
			}	
		}
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User Not Found");
		}
		User user = optionalUser.get();
		Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
		if(optionalRestaurant.isEmpty()) {
			throw new RestaurantNotFoundException("Restaurant Not Found");
		}
		Restaurant restaurant = optionalRestaurant.get();
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		Address addressToDelivery = optionalAddress.get();
		if(restaurant.getAddress().getCity() != addressToDelivery.getCity()) {
			throw new RestaurantDontDeliveryException("Addres different form restaurant address city");
			
		}
		order.setFoods(list);
		order.setUser(user);
		order.setTotalValue(sum);
		order.setStatus(OrderStatus.IN_PROCESS);
		order.setAddress(addressToDelivery);
		order.setRestaurant(restaurant);
		
		return repository.save(order);
	}

	@Override
	public void deleteById(Long orderId) {
		Order order = repository.findById(orderId).get();
		List<OrderFood> foods = order.getFoods();
		for (OrderFood orderFood : foods) {
			Food food = orderFood.getFood();
			food.setStock(food.getStock() + orderFood.getQuantity());
			foodRepository.save(food);
		}
		if(order.getStatus() == OrderStatus.PAID) {
			List<Delivery> deliverys = deliveryRepository.findAll(); 
			for (Delivery delivery : deliverys) {
				if(delivery.getOrder() == order) {
					delivery.setOrder(null);
					deliveryRepository.save(delivery);
				}
			}
		}
		
		repository.deleteById(orderId);
	}

	@Override
	public Order paidOrder(Long orderId) {
		Order order = repository.findById(orderId).get();
		order.setStatus(OrderStatus.PAID);
		Delivery delivery = Delivery.builder().order(order).status(DeliveryStatus.WAINTING_FOR_RESTAURANT_APROVE)
			.selectAddress(order.getAddress()).build();
		deliveryRepository.save(delivery);
		return order;
	}



}
