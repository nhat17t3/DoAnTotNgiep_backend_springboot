package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Transporter;

public interface TransporterRepository extends JpaRepository<Transporter, Integer> {

}
