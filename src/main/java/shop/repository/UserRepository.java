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
	@Query("SELECT e FROM User e WHERE e.email LIKE  %:name% OR e.phone LIKE %:name% ORDER BY e.id ")
	List<User> findAllByNameAndPage(@Param("name") String name, Pageable pageable);
	
	Optional<User> findByUsername( String username);

	Optional<User> findByEmail( String email);

	Boolean existsByUsername( String username);

	Boolean existsByEmail( String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

}
