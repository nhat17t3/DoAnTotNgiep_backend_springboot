package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

	@Query("SELECT e FROM Voucher e ORDER BY e.createdAt DESC")
	List<Voucher> findAllPage(Pageable pageable);
	
	@Query("SELECT e FROM Voucher e WHERE e.code LIKE  %:key% ORDER BY e.id ")
	List<Voucher> searchByCode(@Param("key") String key, Pageable pageable);
}
