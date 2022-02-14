package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shop.entity.Rating;
import shop.entity.UserProductKey;

public interface RatingRepository extends JpaRepository<Rating, UserProductKey> {
	@Query("SELECT e FROM Rating e ")
	List<Rating> findAllPage(Pageable pageable);
	
	List<Rating> findAllByProductId(int productId,Pageable pageable);
	
	
	
	
}
