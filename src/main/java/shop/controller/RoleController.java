package shop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.DTO.ResponseObject;
import shop.entity.Role;
import shop.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/roles")
	public ResponseEntity<ResponseObject> getListRole() {
		List<Role> list = roleService.findAll();
//		if (list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all Role success", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/roles/{id}")
	public ResponseEntity<ResponseObject> getRoleById(@PathVariable(value = "id") int id) {
		Role item = roleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Role by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/roles")
	public ResponseEntity<ResponseObject> createRole(@RequestBody Role form) {
		Role item = new Role();
		item.setName(form.getName());
		item.setCreatedAt(LocalDateTime.now());
		Role newItem = roleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Role success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/roles/{id}")
	public ResponseEntity<ResponseObject> updateRole(@PathVariable(value = "id") int id,
			@RequestBody Role form) {
		Role item = roleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setName(form.getName());
		item.setUpdatedAt(LocalDateTime.now());

		Role updateItem = roleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Role success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/roles/{id}")
	public ResponseEntity<ResponseObject> deleteRole(@PathVariable(value = "id") int id) {
		Role item = roleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		roleService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Role success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

}
