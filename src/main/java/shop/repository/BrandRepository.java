package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}
