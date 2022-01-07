package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Feedback;
import shop.repository.FeedbackRepository;
import shop.service.FeedbackService;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired FeedbackRepository feedbackRepository;

	@Override
	public List<Feedback> findAll() {
		// TODO Auto-generated method stub
		return feedbackRepository.findAll();
	}

	@Override
	public Feedback findById(int id) {
		// TODO Auto-generated method stub
		return feedbackRepository.findById(id).orElse(null);
	}

	@Override
	public Feedback save(Feedback brand) {
		// TODO Auto-generated method stub
		return feedbackRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		feedbackRepository.deleteById(id);
		
	}



}
