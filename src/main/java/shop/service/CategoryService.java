package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category findById(int id);

	Category save(Category cate);

	void delete(int id);

//	bo sung them

	List<Category> findAllAndPage(Pageable pageable);

	List<Category> findAllByNameAndPage(String name, Pageable pageable);

}
