package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(int id);

	Product save(Product brand);

	void delete(int id);
	
//	bo sung them
	
	Long count();

//	find all + phan trang
	List<Product> findAllPage(Pageable pageable);
	
//	search
	List<Product> searchByNameOrCode(String key, Pageable pageable);
	
	List<Product> findAllCreatedAtDESC(Pageable pageable);
	
	List<Product> findAllPriceASC( Pageable pageable);
	
	List<Product> findAllPriceDESC( Pageable pageable);
	
	List<Product> findAllByBrandId(int brandId, Pageable pageable);

	List<Product> findAllByCategoryId(int categoryId, Pageable pageable);
	
	List<Product> findAllByCategorieIdAndBrandId(int categoryId,int brandId, Pageable pageable);
}
