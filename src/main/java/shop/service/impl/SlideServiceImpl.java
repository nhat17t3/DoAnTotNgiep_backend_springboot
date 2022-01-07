package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Slide;
import shop.repository.SlideRepository;
import shop.service.SlideService;

@Service
@Transactional
public class SlideServiceImpl implements SlideService{
	
	@Autowired SlideRepository slideRepository;

	@Override
	public List<Slide> findAll() {
		// TODO Auto-generated method stub
		return slideRepository.findAll();
	}

	@Override
	public Slide findById(int id) {
		// TODO Auto-generated method stub
		return slideRepository.findById(id).orElse(null);
	}

	@Override
	public Slide save(Slide slide) {
		// TODO Auto-generated method stub
		return slideRepository.save(slide);
	}

	@Override
	public void delete(int id) {
		slideRepository.deleteById(id);
		
	}



}
