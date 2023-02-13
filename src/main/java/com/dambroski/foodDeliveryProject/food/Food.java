package com.dambroski.foodDeliveryProject.food;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.dambroski.foodDeliveryProject.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
	
	@Id
	@GeneratedValue(generator = "food_id",strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "food_id",sequenceName = "food_id",allocationSize = 1)
	private Long foodId;
	
	private String name;
	private double price;
	private String description;
	
	private int stock;
	
	@ElementCollection(targetClass = Category.class)
	private List<Category> category;
	
	@JsonIncludeProperties({"restaurantId","name"})
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "restaurant_id",referencedColumnName = "restaurantId")
	private Restaurant restaurant;
	
}
