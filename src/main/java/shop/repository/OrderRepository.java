package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//	find all / sort by id / paging
	@Query("SELECT e FROM Order e ORDER BY e.id ASC")
	List<Order> findAllPage(Pageable pageable);

//	search by name /sort by id/ paging
	@Query("SELECT e FROM Order e WHERE e.nameReceiver LIKE  %:key% OR e.phoneReceiver LIKE %:key% ORDER BY e.id ")
	List<Order> findAllByNameOrPhoneReceiver(@Param("key") String key, Pageable pageable);

	List<Order> findAllByUserId(int userID, Pageable pageable);

	List<Order> findAllByStatus(int status, Pageable pageable);
}
