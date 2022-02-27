//package shop.controller;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import shop.DTO.ResponseObject;
//import shop.entity.Payment;
//import shop.service.PaymentService;
//
//@RestController
//@RequestMapping("/api")
//public class PaymentController {
//
//	@Autowired
//	PaymentService paymentService;
//
//	@GetMapping("/payments")
//	public ResponseEntity<ResponseObject> getListPayment() {
//		List<Payment> list = paymentService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Payment success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
//
//	@GetMapping("/payments/{id}")
//	public ResponseEntity<ResponseObject> getPaymentById(@PathVariable(value = "id") int id) {
//		Payment item = paymentService.findById(id);
//		if (item == null) {
//			return ResponseEntity.notFound().build();
//		}
//		ResponseObject resposeObject = new ResponseObject("success", "find Payment by id success", item);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PostMapping("/payments")
//	public ResponseEntity<ResponseObject> createPayment(@RequestBody Payment form) {
//		Payment item = new Payment(form.getName(), form.getDescription(), form.getIsActive(), LocalDateTime.now());
//		Payment newItem = paymentService.save(item);
//		ResponseObject resposeObject = new ResponseObject("success", "create Payment success", newItem);
//		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@PutMapping("/payments/{id}")
//	public ResponseEntity<ResponseObject> updatePayment(@PathVariable(value = "id") int id,
//			@RequestBody Payment form) {
//		Payment item = paymentService.findById(id);
//		if (item == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		item.setName(form.getName());
//		item.setDescription(form.getDescription());
//		item.setIsActive(form.getIsActive());
//		item.setUpdatedAt(LocalDateTime.now());
//
//		Payment updateItem = paymentService.save(item);
//		ResponseObject resposeObject = new ResponseObject("success", "update Payment success", updateItem);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@DeleteMapping("/payments/{id}")
//	public ResponseEntity<ResponseObject> deletePayment(@PathVariable(value = "id") int id) {
//		Payment item = paymentService.findById(id);
//		if (item == null) {
//			return ResponseEntity.notFound().build();
//		}
//		paymentService.delete(id);
//		ResponseObject resposeObject = new ResponseObject("success", "delete Payment success", "");
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
////
////	@GetMapping("/categories/search")
////	public ResponseEntity<ResponseObject> searchCategoryByNamePage(@RequestParam(value = "q", required = true) String q,
////			@RequestParam(value = "limit", required = false) int limit,
////			@RequestParam(value = "page", required = false) int page) {
////		Pageable pageable = PageRequest.of(page, limit);
////		List<Category> listCate = categoryService.findAllByNameAndPage(q, pageable);
//////		if (listCate.isEmpty()) {
//////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//////		}
////		ResponseObject resposeObject = new ResponseObject("success", "search category by name  ", listCate);
////		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
////	}
////
////	@GetMapping("/categories")
////	public ResponseEntity<ResponseObject> getListCategoryPage(@RequestParam(value = "limit", required = true) int limit,
////			@RequestParam(value = "page", required = true) int page) {
////		Pageable pageable = PageRequest.of(page, limit);
////		List<Category> listCate = categoryService.findAllAndPage(pageable);
//////		if (listCate.isEmpty()) {
//////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//////		}
////		ResponseObject resposeObject = new ResponseObject("success", "find all cate by page", listCate);
////		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
////	}
//}
