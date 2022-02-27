//package shop.service.impl;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import shop.entity.Transporter;
//import shop.repository.TransporterRepository;
//import shop.service.TransporterService;
//
//@Service
//@Transactional
//public class TransporterServiceImpl implements TransporterService{
//	
//	@Autowired TransporterRepository transporterRepository;
//
//	@Override
//	public List<Transporter> findAll() {
//		// TODO Auto-generated method stub
//		return transporterRepository.findAll();
//	}
//
//	@Override
//	public Transporter findById(int id) {
//		// TODO Auto-generated method stub
//		return transporterRepository.findById(id).orElse(null);
//	}
//
//	@Override
//	public Transporter save(Transporter brand) {
//		// TODO Auto-generated method stub
//		return transporterRepository.save(brand);
//	}
//
//	@Override
//	public void delete(int id) {
//		transporterRepository.deleteById(id);
//		
//	}
//
//
//
//}
