package com.dambroski.foodDeliveryProject.deliveryBoy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dambroski.foodDeliveryProject.delivery.Delivery;

@RestController
@RequestMapping("/deliveryBoy")
public class DeliveryBoyController {
	
	@Autowired
	DeliveryBoyService service;
	
	@GetMapping("/getAll")
	public List<DeliveryBoy> getAll(){
		return service.getAll();
	}
	
	@PostMapping("/post/{userId}")
	public DeliveryBoy postDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy,@PathVariable("userId") Long userId) {
		return service.postDelivery(deliveryBoy,userId);
	}
	
	@PutMapping("/deliveryAOrder/{deliveryId}/{deliveryBoyId}/{code}")
	public Delivery deliveryAOder(@PathVariable("deliveryId") Long deliveryId,@PathVariable("deliveryBoyId") Long deliveryBoyId,
			@PathVariable("code") String code){
		return service.deliveryFood(deliveryBoyId,deliveryId,code);
	}

}
