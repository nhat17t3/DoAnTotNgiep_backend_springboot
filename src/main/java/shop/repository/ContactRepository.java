package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
