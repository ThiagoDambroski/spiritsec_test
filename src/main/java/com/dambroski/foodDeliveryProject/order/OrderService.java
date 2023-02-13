package com.dambroski.foodDeliveryProject.order;

import java.util.List;

public interface OrderService {

	List<Order> getAll();

	Order post(Order order,Long userId, Long addressId, Long restaurantId) throws Exception;

	void deleteById(Long orderId);

	Order paidOrder(Long orderId);


}
