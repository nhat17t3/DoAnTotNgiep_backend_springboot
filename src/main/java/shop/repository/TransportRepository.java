//package shop.repository;
//
//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//
//import shop.entity.Transport;
//
//public interface TransportRepository extends JpaRepository<Transport, Integer> {
//
//	@Query("SELECT e FROM Transport e ORDER BY e.createdAt DESC")
//	List<Transport> findAllPage(Pageable pageable);
//	
//	@Query("SELECT e FROM Transport e WHERE e.code LIKE  %:key% ORDER BY e.id ")
//	List<Transport> searchByCode(@Param("key") String key, Pageable pageable);
//
//}
