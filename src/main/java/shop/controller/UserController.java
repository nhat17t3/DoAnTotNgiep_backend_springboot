package shop.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shop.DTO.ResponseObject;
import shop.DTO.SignUpRequest;
import shop.entity.Role;
import shop.entity.User;
import shop.service.RoleService;
import shop.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


//	@GetMapping("/users")
//	public ResponseEntity<ResponseObject> getListOrder() {
//		List<Order> list = orderService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Order success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseObject> getUserById(@PathVariable(value = "id") int id) {
		User item = userService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find User by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<ResponseObject> createUser(@RequestBody SignUpRequest form) {

		if (Boolean.TRUE.equals(userService.existsByUsername(form.getUsername()))) {
			ResponseObject resposeObject = new ResponseObject("error", "sign up error", "Username is already taken");
			return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
		}

		if (Boolean.TRUE.equals(userService.existsByEmail(form.getEmail()))) {
			ResponseObject resposeObject = new ResponseObject("error", "sign up error", "Email is already taken");
			return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
		}
		User user = new User();
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setFirstName(form.getFirstName());
		user.setLastName(form.getLastName());
		user.setPhone(form.getPhone());
		String password = passwordEncoder.encode(form.getPassword());
		user.setPassword(password);
		user.setCreatedAt(LocalDateTime.now());

		Set<Role> newRoles = new HashSet<Role>();
		Set<String> roles = form.getRoles();
		for (String role : roles) {
			Role roleItem = roleService.findByName(role);
			newRoles.add(roleItem);
		}
		user.setRoles(newRoles);

		User newItem = userService.save(user);
		

		ResponseObject resposeObject = new ResponseObject("success", "create User success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<ResponseObject> updateUser(@PathVariable(value = "id") int id,
			@RequestBody SignUpRequest form) {
		User item = userService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setFirstName(form.getFirstName());
		item.setLastName(form.getLastName());
		item.setPhone(form.getPhone());
		item.setAddress(form.getAddress());
		item.setBirthday(form.getBirthday());
		item.setGender(form.getGender());
		item.setIsActive(form.getIsActive());
		item.setUpdatedAt(LocalDateTime.now());
		
		if(form.getRoles()!= null)
		{
			Set<Role> newRoles = new HashSet<Role>();
			Set<String> roles = form.getRoles();
			for (String role : roles) {
				Role roleItem = roleService.findByName(role);
				newRoles.add(roleItem);
			}
			item.setRoles(newRoles);
		}
			
		
		User updateItem = userService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update User success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseObject> deleteUser(@PathVariable(value = "id") int id) {
		User item = userService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		userService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete User success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

//
	@GetMapping("/users/search")
	public ResponseEntity<ResponseObject> searchUserByNamePage(@RequestParam(value = "q", required = true) String q,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<User> listCate = userService.findAllByNameAndPage(q, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "search User by name  ", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<ResponseObject> getListUserPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<User> listCate = userService.findAllAndPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll User by page", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
