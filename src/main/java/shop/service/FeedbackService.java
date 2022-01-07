package shop.service;

import java.util.List;

import shop.entity.Feedback;

public interface FeedbackService {

	List<Feedback> findAll();

	Feedback findById(int id);

	Feedback save(Feedback brand);

	void delete(int id);


}
