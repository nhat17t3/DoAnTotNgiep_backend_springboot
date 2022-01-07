package shop.service;

import java.util.List;

import shop.entity.Slide;

public interface SlideService {

	List<Slide> findAll();

	Slide findById(int id);

	Slide save(Slide brand);

	void delete(int id);


}
