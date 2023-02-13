package com.dambroski.foodDeliveryProject.restaurant;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.dambroski.foodDeliveryProject.Address.Address;
import com.dambroski.foodDeliveryProject.food.Food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(generator = "restaurant_id",strategy = GenerationType.IDENTITY)
	private Long restaurantId;
	
	private String name;

	private String description;
	
	
	@OneToMany(mappedBy = "restaurant")
	private List<Food> itens;
	
	@OneToOne
	private Address address;

	
}
