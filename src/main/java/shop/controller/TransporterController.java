package shop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.DTO.ResponseObject;
import shop.entity.Transporter;
import shop.service.TransporterService;

@RestController
@RequestMapping("/api")
public class TransporterController {

	@Autowired
	TransporterService transporterService;

	@GetMapping("/transporters")
	public ResponseEntity<ResponseObject> getListTransporter() {
		List<Transporter> list = transporterService.findAll();
//		if (list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all transporter success", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/transporters/{id}")
	public ResponseEntity<ResponseObject> getTransporterById(@PathVariable(value = "id") int id) {
		Transporter item = transporterService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Transporter by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/transporters")
	public ResponseEntity<ResponseObject> createTransporter(@RequestBody Transporter form) {
		Transporter item = new Transporter(form.getName(), form.getLink(), form.getIsActive(), LocalDateTime.now());
		Transporter newItem = transporterService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Transporter success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/transporters/{id}")
	public ResponseEntity<ResponseObject> updateTransporter(@PathVariable(value = "id") int id,
			@RequestBody Transporter form) {
		Transporter item = transporterService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setName(form.getName());
		item.setLink(form.getLink());
		item.setIsActive(form.getIsActive());
		item.setUpdatedAt(LocalDateTime.now());

		Transporter updateItem = transporterService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Transporter success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/transporters/{id}")
	public ResponseEntity<ResponseObject> deleteTransporter(@PathVariable(value = "id") int id) {
		Transporter item = transporterService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		transporterService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Transporter success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
//
//	@GetMapping("/categories/search")
//	public ResponseEntity<ResponseObject> searchCategoryByNamePage(@RequestParam(value = "q", required = true) String q,
//			@RequestParam(value = "limit", required = false) int limit,
//			@RequestParam(value = "page", required = false) int page) {
//		Pageable pageable = PageRequest.of(page, limit);
//		List<Category> listCate = categoryService.findAllByNameAndPage(q, pageable);
////		if (listCate.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "search category by name  ", listCate);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
//
//	@GetMapping("/categories")
//	public ResponseEntity<ResponseObject> getListCategoryPage(@RequestParam(value = "limit", required = true) int limit,
//			@RequestParam(value = "page", required = true) int page) {
//		Pageable pageable = PageRequest.of(page, limit);
//		List<Category> listCate = categoryService.findAllAndPage(pageable);
////		if (listCate.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all cate by page", listCate);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
}
