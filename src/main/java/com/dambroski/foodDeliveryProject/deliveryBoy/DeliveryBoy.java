package com.dambroski.foodDeliveryProject.deliveryBoy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.dambroski.foodDeliveryProject.Address.Address;
import com.dambroski.foodDeliveryProject.User.User;
import com.dambroski.foodDeliveryProject.delivery.Delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBoy {
	
	
	@Id
	@GeneratedValue(generator = "delivery_boy_id",strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private User user;
	
	private String region;
	
	@OneToMany
	private List<Delivery> deliveries;
}
