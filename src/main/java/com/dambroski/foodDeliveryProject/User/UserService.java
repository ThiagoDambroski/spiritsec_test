package com.dambroski.foodDeliveryProject.User;

import java.util.List;

import com.dambroski.foodDeliveryProject.Address.Address;

public interface UserService {

	List<User> getAll();

	User getUserById(Long id);

	User postUser(User user);

	void deleteById(Long id);

	User editUser(User user, Long id);

	User postUserRestaurant(User user);

	User postUserDelivery(User user);

	User postUserAdmin(User user);

	User addAddress(Address address, Long userId);

	User editAddress(Address address, Long userId, Long addressId);

}
