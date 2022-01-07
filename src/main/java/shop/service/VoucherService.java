package shop.service;

import java.util.List;

import shop.entity.Voucher;

public interface VoucherService {

	List<Voucher> findAll();

	Voucher findById(int id);

	Voucher save(Voucher brand);

	void delete(int id);


}
