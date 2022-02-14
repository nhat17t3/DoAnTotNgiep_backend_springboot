package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.CategoryArticle;

public interface CategoryArticleRepository extends JpaRepository<CategoryArticle, Integer> {

}
