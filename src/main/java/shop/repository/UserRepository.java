package shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
//	find all / sort by id / paging
	@Query("SELECT e FROM User e ORDER BY e.id ASC")
	List<User> findAllAndPage(Pageable pageable);

//	search by name /sort by id/ paging
	@Query("SELECT e FROM User e WHERE e.email LIKE  %:key% OR e.phone LIKE %:key% ORDER BY e.id ")
	List<User> searchByNameOrEmail(@Param("key") String key, Pageable pageable);
	
	Optional<User> findByUsername( String username);

	Optional<User> findByEmail( String email);

	Boolean existsByUsername( String username);

	Boolean existsByEmail( String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

}
