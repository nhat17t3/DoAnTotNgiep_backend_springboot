package shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Favourite;
import shop.entity.UserProductKey;

public interface FavouriteRepository extends JpaRepository<Favourite, UserProductKey> {

	List<Favourite> findAllByUserId(int userId);

}
