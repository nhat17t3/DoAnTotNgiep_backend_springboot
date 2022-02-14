package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.entity.Favourite;
import shop.entity.UserProductKey;
import shop.repository.FavouriteRepository;
import shop.service.FavouriteService;

@Service
@Transactional
public class FavouriteServiceImpl implements FavouriteService{
	
	@Autowired FavouriteRepository favouriteRepository;

	@Override
	public List<Favourite> findAll() {
		// TODO Auto-generated method stub
		return favouriteRepository.findAll();
	}

	@Override
	public Favourite findById(UserProductKey key) {
		// TODO Auto-generated method stub
		return favouriteRepository.findById(key).orElse(null);
	}

	@Override
	public Favourite save(Favourite cate) {
		// TODO Auto-generated method stub
		return favouriteRepository.save(cate);
	}

	@Override
	public void delete(UserProductKey key) {
		favouriteRepository.deleteById(key);
		
	}

	@Override
	public List<Favourite> findAllByUserId(int userId) {
		// TODO Auto-generated method stub
		return favouriteRepository.findAllByUserId(userId);
	}



}
