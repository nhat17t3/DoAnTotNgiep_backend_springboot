package shop.service;

import java.util.List;

import shop.entity.Brand;

public interface BrandService {

	List<Brand> findAll();

	Brand findById(int id);

	Brand save(Brand brand);

	void delete(int id);


}
