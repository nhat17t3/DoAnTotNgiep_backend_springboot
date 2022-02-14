package shop.service;

import java.util.List;

import shop.entity.Role;

public interface RoleService {

	List<Role> findAll();

	Role findById(int id);
	
	Role findByName(String name);

	Role save(Role brand);

	void delete(int id);


}
