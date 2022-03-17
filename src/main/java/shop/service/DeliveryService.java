package shop.service;

import java.util.List;

import shop.entity.Delivery;

public interface DeliveryService {

	List<Delivery> findAll();

	Delivery findById(int id);

	Delivery save(Delivery delivery);

	void delete(int id);
	
	


}
