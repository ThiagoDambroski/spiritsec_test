package com.dambroski.foodDeliveryProject.delivery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	
	@Query("select d from Delivery d where d.selectAddress.city LIKE '%'||:region||'%'")
	List<Delivery> findDeliveryByRegion(@Param("region") String region);

}
