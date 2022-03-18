package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.Order;
import shop.repository.OrderRepository;
import shop.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired OrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public Order save(Order cate) {
		// TODO Auto-generated method stub
		return orderRepository.save(cate);
	}

	@Override
	public void delete(int id) {
		orderRepository.deleteById(id);
		
	}


	@Override
	public List<Order> findAllPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return   orderRepository.findAllPage(pageable);
	}

	
	@Override
	public List<Order> findAllByNameOrPhoneReceiver(String key, Pageable pageable) {
		// TODO Auto-generated method stub
		return   orderRepository.findAllByNameOrPhoneReceiver(key, pageable);
	}

	@Override
	public List<Order> findAllByUserId(int userID, Pageable pageable) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByUserId(userID, pageable);
	}

	@Override
	public List<Order> findAllByStatus(int status, Pageable pageable) {
		// TODO Auto-generated method stub
		return orderRepository.findAllByStatus(status, pageable);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return orderRepository.count();
	}

	@Override
	public List<Order> filterAndPage(String key, int status, Pageable pageable) {
		// TODO Auto-generated method stub
		return orderRepository.filterAndPage(key, status, pageable);
	}

}
