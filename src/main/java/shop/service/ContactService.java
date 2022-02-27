package shop.service;

import java.util.List;

import shop.entity.Contact;

public interface ContactService {

	List<Contact> findAll();

	Contact findById(int id);

	Contact save(Contact brand);

	void delete(int id);


}
