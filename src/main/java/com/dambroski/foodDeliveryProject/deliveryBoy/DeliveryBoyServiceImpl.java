package com.dambroski.foodDeliveryProject.deliveryBoy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dambroski.foodDeliveryProject.User.User;
import com.dambroski.foodDeliveryProject.User.UserRepository;
import com.dambroski.foodDeliveryProject.delivery.Delivery;
import com.dambroski.foodDeliveryProject.delivery.DeliveryRepository;
import com.dambroski.foodDeliveryProject.delivery.DeliveryService;
import com.dambroski.foodDeliveryProject.delivery.DeliveryStatus;
import com.dambroski.foodDeliveryProject.error.DeliveryBoyNotFoundException;
import com.dambroski.foodDeliveryProject.error.DeliveryNotFoundException;
import com.dambroski.foodDeliveryProject.error.MissMatchException;

@Service
public class DeliveryBoyServiceImpl implements DeliveryBoyService {
	
	@Autowired
	DeliveryBoyRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DeliveryRepository deliveryRepository;

	@Override
	public List<DeliveryBoy> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public DeliveryBoy postDelivery(DeliveryBoy deliveryBoy,Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User Not Found");
		}
		User user = optionalUser.get();
		if(user.getRole().equals("delivery")) {
			deliveryBoy.setUser(user);
		}else {
			throw new BadCredentialsException("user is not a deliveryBoy");
		}
		return repository.save(deliveryBoy);
	}

	@Override
	public Delivery deliveryFood(Long deliveryBoyId, Long deliveryId,String code) {
		Optional<DeliveryBoy> optionalBoy = repository.findById(deliveryBoyId);
		if(optionalBoy.isEmpty()) {
			throw new DeliveryBoyNotFoundException("Delivery Boy Not Found");
		}
		DeliveryBoy boy = optionalBoy.get();
		Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
		if(optionalDelivery.isEmpty()) {
			throw new DeliveryNotFoundException("Delivery Boy Not Found");
		}
		Delivery delivery = optionalDelivery .get();
		User user = delivery.getOrder().getUser();
		if(delivery.getBoy()!= boy) {
			throw new MissMatchException("delivery id and moto code are not the same");
		}
		if(code != user.getCode()) {
			throw new MissMatchException("Code is not the same");
		}
		delivery.setStatus(DeliveryStatus.DELIVERY);
		deliveryRepository.save(delivery);
	
		
		return delivery;
	}

	



}
