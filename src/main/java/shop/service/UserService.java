package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.User;

public interface UserService {

	List<User> findAll();

	User findById(int id);

	User save(User brand);

	void delete(int id);
	
//	bo sung them
	
	Long count();

	List<User> findAllAndPage(Pageable pageable);

//	List<User> findAllByNameAndPage(String name, Pageable pageable);
	
	List<User> searchByNameOrEmail(String key, Pageable pageable);


	User findByUsername( String username);

	User findByEmail( String email);

	Boolean existsByUsername( String username);

	Boolean existsByEmail( String email);

	User findByUsernameOrEmail(String username, String email);
}
