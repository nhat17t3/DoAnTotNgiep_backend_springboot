//package shop.service.impl;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import shop.entity.Payment;
//import shop.repository.PaymentRepository;
//import shop.service.PaymentService;
//
//@Service
//@Transactional
//public class PaymentServiceImpl implements PaymentService{
//	
//	@Autowired PaymentRepository paymentRepository;
//
//	@Override
//	public List<Payment> findAll() {
//		// TODO Auto-generated method stub
//		return paymentRepository.findAll();
//	}
//
//	@Override
//	public Payment findById(int id) {
//		// TODO Auto-generated method stub
//		return paymentRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public Payment save(Payment payment) {
//		// TODO Auto-generated method stub
//		return paymentRepository.save(payment);
//	}
//
//	@Override
//	public void delete(int id) {
//		paymentRepository.deleteById(id);
//		
//	}
//
//
//
//}
