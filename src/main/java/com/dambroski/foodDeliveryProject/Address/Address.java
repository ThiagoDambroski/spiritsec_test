package com.dambroski.foodDeliveryProject.Address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	
	
	@Id
	@GeneratedValue(generator = "adress_id",strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String type;
	private Long typeId;
	
	private String state;
	private String city;
	private String address;
	
}
