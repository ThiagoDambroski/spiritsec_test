package com.dambroski.foodDeliveryProject.orderFood;

import java.util.List;

public interface OrderFoodService {

	List<OrderFood> getAll();

	OrderFood postOrderFood(OrderFood orderFood, Long foodId);

}
