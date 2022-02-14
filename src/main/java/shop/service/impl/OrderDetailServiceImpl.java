package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.OrderDetail;
import shop.entity.OrderProductKey;
import shop.repository.OrderDetailRepository;
import shop.service.OrderDetailService;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return orderDetailRepository.findAll();
	}

	@Override
	public OrderDetail findById(OrderProductKey id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findById(id).orElse(null);
	}

	@Override
	public OrderDetail save(OrderDetail cate) {
		// TODO Auto-generated method stub
		return orderDetailRepository.save(cate);
	}

	@Override
	public void delete(OrderProductKey id) {
		orderDetailRepository.deleteById(id);
		
	}


	
}
