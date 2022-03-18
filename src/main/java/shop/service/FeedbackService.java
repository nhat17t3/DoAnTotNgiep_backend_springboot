package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Feedback;

public interface FeedbackService {

	List<Feedback> findAll();
	
	Feedback findById(int id);

	Feedback save(Feedback brand);

	void delete(int id);

//	bo sung them
	List<Feedback> findAllPage(Pageable pageable);
	
//	List<Feedback> findAllByIsRead(boolean isRead, Pageable pageable);
	
	Long count();

}
