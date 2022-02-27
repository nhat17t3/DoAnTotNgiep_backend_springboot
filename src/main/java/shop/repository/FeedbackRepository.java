package shop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import shop.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

	@Query("SELECT e FROM Feedback e ")
	List<Feedback> findAllPage(Pageable pageable);
	
//	List<Feedback> findAllByIsRead(boolean isRead, Pageable pageable);
}
