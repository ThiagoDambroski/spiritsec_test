package com.dambroski.foodDeliveryProject.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dambroski.foodDeliveryProject.deliveryBoy.DeliveryBoy;
import com.dambroski.foodDeliveryProject.deliveryBoy.DeliveryBoyRepository;

@Service
public class DeliveryServiceImpl implements  DeliveryService{
	
	@Autowired
	DeliveryRepository repository;
	
	@Autowired
	DeliveryBoyRepository boyRepository;
	

	@Override
	public List<Delivery> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Delivery> getByRegionDelivery(Long boyId) {
		DeliveryBoy boy = boyRepository.findById(boyId).get();
		String deliveryRegion = boy.getRegion();
		return repository.findDeliveryByRegion(deliveryRegion);
	}

	@Override
	public Delivery addDeliveryboy(Long deliveryId, Long boyId) {
		DeliveryBoy boy = boyRepository.findById(boyId).get();
		Delivery delivery = repository.findById(deliveryId).get();
		delivery.setBoy(boy);
		return repository.save(delivery);
	}

}
