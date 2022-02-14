package shop.service;

import java.util.List;

import shop.entity.Favourite;
import shop.entity.UserProductKey;

public interface FavouriteService {

	List<Favourite> findAll();

	Favourite findById(UserProductKey key);

	Favourite save(Favourite brand);

	void delete(UserProductKey key);
	
//	bo sung them


	List<Favourite> findAllByUserId(int userId);


}
