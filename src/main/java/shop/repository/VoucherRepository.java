package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Integer> {

}
