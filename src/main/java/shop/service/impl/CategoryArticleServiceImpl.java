package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.CategoryArticle;
import shop.repository.CategoryArticleRepository;
import shop.service.CategoryArticleService;

@Service
@Transactional
public class CategoryArticleServiceImpl implements CategoryArticleService{
	
	@Autowired CategoryArticleRepository categoryArticleRepository;

	@Override
	public List<CategoryArticle> findAll() {
		// TODO Auto-generated method stub
		return categoryArticleRepository.findAll();
	}

	@Override
	public CategoryArticle findById(int id) {
		// TODO Auto-generated method stub
		return categoryArticleRepository.findById(id).orElse(null);
	}

	@Override
	public CategoryArticle save(CategoryArticle brand) {
		// TODO Auto-generated method stub
		return categoryArticleRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		categoryArticleRepository.deleteById(id);
		
	}



}
