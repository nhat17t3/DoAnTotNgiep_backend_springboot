package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Delivery;
import shop.repository.DeliveryRepository;
import shop.service.DeliveryService;

@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired DeliveryRepository deliveryRepository;

	@Override
	public List<Delivery> findAll() {
		// TODO Auto-generated method stub
		return deliveryRepository.findAll();
	}

	@Override
	public Delivery findById(int id) {
		// TODO Auto-generated method stub
		return deliveryRepository.findById(id).orElse(null);
	}

	@Override
	public Delivery save(Delivery brand) {
		// TODO Auto-generated method stub
		return deliveryRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		deliveryRepository.deleteById(id);
		
	}



}
