package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.Product;
import shop.repository.ProductRepository;
import shop.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product save(Product cate) {
		// TODO Auto-generated method stub
		return productRepository.save(cate);
	}

	@Override
	public void delete(int id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<Product> findAllPage(Pageable pageable) {
		// TODO Auto-generated method stub
		System.out.println("ccccccccccccccccccccccccccccc");
		return productRepository.findAllPage(pageable);
	}

	@Override
	public List<Product> searchByNameOrCode(String key, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.searchByNameOrCode(key, pageable);
	}

	@Override
	public List<Product> findAllCreatedAtDESC(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAllCreatedAtDESC(pageable);
	}

	@Override
	public List<Product> findAllPriceASC(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAllPriceASC(pageable);
	}

	@Override
	public List<Product> findAllPriceDESC(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAllPriceDESC(pageable);
	}

	@Override
	public List<Product> findAllByBrandId(int brandId, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAllByBrandId(brandId, pageable);
	}

	@Override
	public List<Product> findAllByCategorieId(int categoryId, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAllByCategorieId(categoryId, pageable);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return productRepository.count();
	}


	

}
