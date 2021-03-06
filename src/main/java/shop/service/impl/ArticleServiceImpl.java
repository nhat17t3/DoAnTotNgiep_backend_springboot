package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.Article;
import shop.entity.Product;
import shop.repository.ArticleRepository;
import shop.service.ArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired ArticleRepository articleRepository;

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}

	@Override
	public Article findById(int id) {
		// TODO Auto-generated method stub
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public Article save(Article cate) {
		// TODO Auto-generated method stub
		return articleRepository.save(cate);
	}

	@Override
	public void delete(int id) {
		articleRepository.deleteById(id);
		
	}


	@Override
	public List<Article> findAllAndPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return   articleRepository.findAllAndPage(pageable);
	}

	
	@Override
	public List<Article> findAllByNameAndPage(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return   articleRepository.findAllByNameAndPage(name, pageable);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return articleRepository.count();
	}

	@Override
	public List<Article> findAllByCategoryArticleId(int categoryArticleId, Pageable pageable) {
		// TODO Auto-generated method stub
		return articleRepository.findAllByCategoryArticleId(categoryArticleId, pageable);
	}

	@Override
	public List<Article> filterAndPage(String name, int categoryArticleId, Pageable pageable) {
		// TODO Auto-generated method stub
		return articleRepository.filterAndPage(name, categoryArticleId, pageable);
	}

}
