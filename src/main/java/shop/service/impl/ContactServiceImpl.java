package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Contact;
import shop.repository.ContactRepository;
import shop.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{
	
	@Autowired ContactRepository contactRepository;

	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	@Override
	public Contact findById(int id) {
		// TODO Auto-generated method stub
		return contactRepository.findById(id).orElse(null);
	}

	@Override
	public Contact save(Contact brand) {
		// TODO Auto-generated method stub
		return contactRepository.save(brand);
	}

	@Override
	public void delete(int id) {
		contactRepository.deleteById(id);
		
	}



}
