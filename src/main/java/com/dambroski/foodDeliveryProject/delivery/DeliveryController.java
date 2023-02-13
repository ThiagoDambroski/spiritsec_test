package com.dambroski.foodDeliveryProject.delivery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	DeliveryService service;
	
	
	@GetMapping("getAll")
	public List<Delivery> getAllDelivery(){
		return service.getAll();
	}
	
	@GetMapping("/getByRegionDeliveryBoy/{boyId}")
	public List<Delivery> getByRegionDelivery(@PathVariable("boyId") Long boyId){
		return service.getByRegionDelivery(boyId);
	}
	
	@PutMapping("/addDeliveryBoy/{deliveryId}/{deliveryboyId}/{code}")
	public Delivery addDeliveryboy(@PathVariable("deliveryId") Long deliveryId,@PathVariable("deliveryBoyId")Long boyId) {
		return service.addDeliveryboy(deliveryId,boyId);
	}
	


	
}
