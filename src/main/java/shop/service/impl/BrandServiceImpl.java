package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Brand;
import shop.repository.BrandRepository;
import shop.service.BrandService;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{
	
	@Autowired BrandRepository brandRepository;

	@Override
	public List<Brand> findAll() {
		// TODO Auto-generated method stub
		return brandRepository.findAll();
	}

	@Override
	public Brand findById(int id) {
		// TODO Auto-generated method stub
		return brandRepository.findById(id).orElse(null);
	}

	@Override
	public Brand save(Brand brand) {
		// TODO Auto-generated method stub
		return brandRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		brandRepository.deleteById(id);
		
	}



}
