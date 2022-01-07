package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Voucher;
import shop.repository.VoucherRepository;
import shop.service.VoucherService;

@Service
@Transactional
public class VoucherServiceImpl implements VoucherService{
	
	@Autowired VoucherRepository voucherRepository;

	@Override
	public List<Voucher> findAll() {
		// TODO Auto-generated method stub
		return voucherRepository.findAll();
	}

	@Override
	public Voucher findById(int id) {
		// TODO Auto-generated method stub
		return voucherRepository.findById(id).orElse(null);
	}

	@Override
	public Voucher save(Voucher brand) {
		// TODO Auto-generated method stub
		return voucherRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		voucherRepository.deleteById(id);
		
	}



}
