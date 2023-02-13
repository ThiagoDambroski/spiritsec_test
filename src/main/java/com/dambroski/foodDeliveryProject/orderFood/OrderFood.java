package com.dambroski.foodDeliveryProject.orderFood;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.dambroski.foodDeliveryProject.food.Food;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderFood {
	
	@Id
	@GeneratedValue(generator = "order_food_id",strategy = GenerationType.IDENTITY)
	
	private Long orderFoodId;
	
	@OneToOne(targetEntity = Food.class)
	private Food food;
	
	private int quantity;
	
	

}
