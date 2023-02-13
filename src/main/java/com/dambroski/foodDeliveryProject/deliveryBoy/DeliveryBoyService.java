package com.dambroski.foodDeliveryProject.deliveryBoy;

import java.util.List;

import com.dambroski.foodDeliveryProject.delivery.Delivery;

public interface DeliveryBoyService {

	List<DeliveryBoy> getAll();

	DeliveryBoy postDelivery(DeliveryBoy deliveryBoy, Long userId);

	Delivery deliveryFood(Long deliveryBoyId, Long deliveryId, String code);
	
}
