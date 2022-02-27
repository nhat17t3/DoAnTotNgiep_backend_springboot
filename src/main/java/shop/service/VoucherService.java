package shop.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import shop.entity.Voucher;

public interface VoucherService {

	List<Voucher> findAll();

	Voucher findById(int id);

	Voucher save(Voucher brand);

	void delete(int id);
	
//	bo sung them
	Long count();
	
	List<Voucher> findAllPage(Pageable pageable);

	List<Voucher> searchByCode(String key, Pageable pageable);
}
