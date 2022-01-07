package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.Category;
import shop.repository.CategoryRepository;
import shop.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category save(Category cate) {
		// TODO Auto-generated method stub
		return categoryRepository.save(cate);
	}

	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
		
	}


	@Override
	public List<Category> findAllAndPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return   categoryRepository.findAllAndPage(pageable);
	}

	
	@Override
	public List<Category> findAllByNameAndPage(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return   categoryRepository.findAllByNameAndPage(name, pageable);
	}

}
