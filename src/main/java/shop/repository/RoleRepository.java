package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByName(String name);
	
//	Optional<Role> findByName(ERole name);


}
