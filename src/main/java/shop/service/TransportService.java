package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Transport;

public interface TransportService {

	List<Transport> findAll();

	Transport findById(int id);

	Transport save(Transport cate);

	void delete(int id);

//	bo sung them

	List<Transport> findAllPage(Pageable pageable);

//	List<Category> findAllByNameAndPage(String name, Pageable pageable);

}
