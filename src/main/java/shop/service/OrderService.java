package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import shop.entity.Order;

public interface OrderService {

	List<Order> findAll();

	Order findById(int id);

	Order save(Order brand);

	void delete(int id);
	
//	bo sung them
	
	Long count();

	List<Order> findAllPage(Pageable pageable);

	List<Order> findAllByNameOrPhoneReceiver(String key, Pageable pageable);

	List<Order> findAllByUserId(int userID, Pageable pageable);

	List<Order> findAllByStatus(int status, Pageable pageable);
	
	List<Order> filterAndPage( String key,  int status, Pageable pageable);

}
