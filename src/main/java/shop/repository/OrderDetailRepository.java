package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.OrderDetail;
import shop.entity.OrderProductKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderProductKey> {

}
