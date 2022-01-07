package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

//	find all / sort by id / paging
	@Query("SELECT e FROM Category e ORDER BY e.id ASC")
	List<Category> findAllAndPage(Pageable pageable);

//	search by name /sort by id/ paging
	@Query("SELECT e FROM Category e WHERE e.name LIKE  %:name% ORDER BY e.id ")
	List<Category> findAllByNameAndPage(@Param("name") String name, Pageable pageable);
}
