package shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import shop.entity.User;
import shop.repository.UserRepository;
import shop.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired UserRepository userRepository;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User save(User cate) {
		// TODO Auto-generated method stub
		return userRepository.save(cate);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
		
	}


	@Override
	public List<User> findAllAndPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return   userRepository.findAllAndPage(pageable);
	}

	
	@Override
	public List<User> findAllByNameAndPage(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return   userRepository.findAllByNameAndPage(name, pageable);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public Boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findByUsernameOrEmail(String username, String email) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameOrEmail(username, email).orElse(null);
	}

}
