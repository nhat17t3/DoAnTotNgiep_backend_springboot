package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Article;

public interface ArticleService {

	List<Article> findAll();

	Article findById(int id);

	Article save(Article brand);

	void delete(int id);
	
//	bo sung them
	
	Long count();

	List<Article> findAllAndPage(Pageable pageable);

	List<Article> findAllByNameAndPage(String name, Pageable pageable);


}
