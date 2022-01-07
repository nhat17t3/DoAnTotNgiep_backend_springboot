package shop.service;

import java.util.List;

public interface Service <T> {
	
	List<T> findAll();
	
	T findById(int id);
	
	T save (T model);
	
	void delete (int id);
}
