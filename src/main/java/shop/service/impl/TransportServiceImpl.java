package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.Transport;
import shop.repository.TransportRepository;
import shop.service.TransportService;

@Service
@Transactional
public class TransportServiceImpl implements TransportService{
	
	@Autowired TransportRepository transportRepository;

	@Override
	public List<Transport> findAll() {
		// TODO Auto-generated method stub
		return transportRepository.findAll();
	}

	@Override
	public Transport findById(int id) {
		// TODO Auto-generated method stub
		return transportRepository.findById(id).orElse(null);
	}

	@Override
	public Transport save(Transport cate) {
		// TODO Auto-generated method stub
		return transportRepository.save(cate);
	}

	@Override
	public void delete(int id) {
		transportRepository.deleteById(id);
		
	}


	@Override
	public List<Transport> findAllPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return   transportRepository.findAllPage(pageable);
	}

	
//	@Override
//	public List<Category> findAllByNameAndPage(String name, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return   categoryRepository.findAllByNameAndPage(name, pageable);
//	}

}
