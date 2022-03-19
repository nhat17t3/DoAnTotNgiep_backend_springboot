package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

//	@Query("SELECT e FROM Product e ORDER BY e.id ASC")
//	List<Product> findAllPage(Pageable pageable);
	
	
	@Query("SELECT e FROM Product e ORDER BY e.id")
	List<Product> findAllPage(Pageable pageable);
	
	@Query("SELECT e FROM Product e ORDER BY e.createdAt ASC")
	List<Product> findAllCreatedAtDESC(Pageable pageable);
	
	@Query("SELECT e FROM Product e ORDER BY e.promotionPrice ASC")
	List<Product> findAllPriceASC(Pageable pageable);
	
	@Query("SELECT e FROM Product e ORDER BY e.promotionPrice DESC")
	List<Product> findAllPriceDESC(Pageable pageable);
	

	@Query("SELECT e FROM Product e WHERE e.name LIKE  %:key% OR e.code LIKE %:key% ORDER BY e.id ")
	List<Product> searchByNameOrCode(@Param("key") String key, Pageable pageable);

	List<Product> findAllByBrandId(int brandId, Pageable pageable);
	
	List<Product> findAllByCategoryId(int categoryId, Pageable pageable);
	
	List<Product> findAllByCategoryIdAndBrandId(int categoryId,int brandId, Pageable pageable);

//	@Query("  SELECT m FROM Product m JOIN m.categories mg WHERE mg.id = :categoryId  ")
//	List<Product> findAllByCategorieId(int categoryId, Pageable pageable);
	
	@Query(value = "SELECT count(*) FROM product", nativeQuery=true)
	long count();
}
