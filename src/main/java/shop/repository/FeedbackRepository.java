package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
