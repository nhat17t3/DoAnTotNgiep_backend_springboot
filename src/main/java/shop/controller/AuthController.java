package shop.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.DTO.LoginRequest;
import shop.DTO.ResponseObject;
import shop.DTO.SignUpRequest;
import shop.entity.Role;
import shop.entity.User;
import shop.security.JwtTokenProvider;
import shop.service.RoleService;
import shop.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/getInfor")
	public ResponseEntity<ResponseObject> getInformationUser(@RequestBody LoginRequest token) {

		System.out.println(token);
		int userID = jwtTokenProvider.getUserIdFromJWT(token.getToken());
		
		User user = userService.findById(userID);

		System.out.println(user);
		ResponseObject resposeObject = new ResponseObject("success", "get information user", user);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<ResponseObject> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication);

		ResponseObject resposeObject = new ResponseObject("success", "Login success", jwt);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PostMapping("/signup")
	public ResponseEntity<ResponseObject> registerUser(@RequestBody SignUpRequest form) {
		if (Boolean.TRUE.equals(userService.existsByUsername(form.getUsername()))) {
			ResponseObject resposeObject = new ResponseObject("error", "Username is already taken","");
			return new ResponseEntity<>(resposeObject, HttpStatus.BAD_REQUEST);
		}

		if (Boolean.TRUE.equals(userService.existsByEmail(form.getEmail()))) {
			ResponseObject resposeObject = new ResponseObject("error", "Email is already taken","");
			return new ResponseEntity<>(resposeObject, HttpStatus.BAD_REQUEST);
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
		user.setIsActive(true);

		Set<Role> newRoles = new HashSet<Role>();
		Set<String> roles = form.getRoles();
		for (String role : roles) {
			Role roleItem = roleService.findByName(role);
			newRoles.add(roleItem);
		}
		user.setRoles(newRoles);

		User newItem = userService.save(user);
		
		ResponseObject resposeObject = new ResponseObject("success", "User registered successfully", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);

//
//		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{userId}")
//				.buildAndExpand(newItem.getId()).toUri();

//		return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "User registered successfully"));
	}
}
