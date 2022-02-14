package shop.service;

import java.util.List;

import shop.entity.CategoryArticle;

public interface CategoryArticleService {

	List<CategoryArticle> findAll();

	CategoryArticle findById(int id);

	CategoryArticle save(CategoryArticle brand);

	void delete(int id);


}
