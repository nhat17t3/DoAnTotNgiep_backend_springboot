package shop.service;

import java.util.List;

import shop.entity.OrderDetail;
import shop.entity.OrderProductKey;

public interface OrderDetailService {

	List<OrderDetail> findAll();

	OrderDetail findById(OrderProductKey id);

	OrderDetail save(OrderDetail brand);

	void delete(OrderProductKey id);
	
//	bo sung them

//	List<Article> findAllAndPage(Pageable pageable);
//
//	List<Article> findAllByNameAndPage(String name, Pageable pageable);


}
