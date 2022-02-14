package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
//	find all / sort by id / paging
	@Query("SELECT e FROM Article e ORDER BY e.id ASC")
	List<Article> findAllAndPage(Pageable pageable);

//	search by name /sort by id/ paging
	@Query("SELECT e FROM Article e WHERE e.name LIKE  %:name% ORDER BY e.id ")
	List<Article> findAllByNameAndPage(@Param("name") String name, Pageable pageable);

}
