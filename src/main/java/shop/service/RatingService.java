package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Rating;
import shop.entity.UserProductKey;

public interface RatingService {

	List<Rating> findAll();

	Rating findById(UserProductKey id);

	Rating save(Rating brand);

	void delete(UserProductKey id);
	
//	bo sung them

	List<Rating> findAllPage(Pageable pageable);
	
	List<Rating> findAllByProductId(int productId,Pageable pageable);




}
