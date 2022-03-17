package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
