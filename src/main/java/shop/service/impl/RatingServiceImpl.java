package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.Rating;
import shop.entity.UserProductKey;
import shop.repository.RatingRepository;
import shop.service.RatingService;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{
	
	@Autowired RatingRepository ratingRepository;

	@Override
	public List<Rating> findAll() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public Rating findById(UserProductKey id) {
		// TODO Auto-generated method stub
		return ratingRepository.findById(id).orElse(null);
	}

	@Override
	public Rating save(Rating cate) {
		// TODO Auto-generated method stub
		return ratingRepository.save(cate);
	}

	@Override
	public void delete(UserProductKey id) {
		ratingRepository.deleteById(id);
		
	}

	@Override
	public List<Rating> findAllPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return ratingRepository.findAllPage(pageable);
	}

	@Override
	public List<Rating> findAllByProductId(int productId, Pageable pageable) {
		// TODO Auto-generated method stub
		return ratingRepository.findAllByProductId(productId, pageable);
	}


	

}
