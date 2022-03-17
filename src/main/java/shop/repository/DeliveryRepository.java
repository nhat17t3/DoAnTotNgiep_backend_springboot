package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

}
