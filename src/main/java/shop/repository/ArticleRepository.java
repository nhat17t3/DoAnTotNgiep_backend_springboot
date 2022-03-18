package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.Article;
import shop.entity.Product;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
//	find all / sort by id / paging
	@Query("SELECT e FROM Article e ORDER BY e.id ASC")
	List<Article> findAllAndPage(Pageable pageable);

//	search by name /sort by id/ paging
	@Query("SELECT e FROM Article e WHERE e.name LIKE  %:name% ORDER BY e.id ")
	List<Article> findAllByNameAndPage(@Param("name") String name, Pageable pageable);
	
	List<Article> findAllByCategoryArticleId(int categoryArticleId, Pageable pageable);

	@Query("SELECT e FROM Article e inner JOIN e.categoryArticle mg WHERE mg.id = :categoryArticleId AND e.name LIKE  %:name%  ORDER BY e.id ")
	List<Article> filterAndPage(@Param("name") String name, @Param("categoryArticleId") int categoryArticleId, Pageable pageable);

}
