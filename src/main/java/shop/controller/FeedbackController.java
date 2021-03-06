package shop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.ResponseObject;
import shop.entity.Feedback;
import shop.entity.Order;
import shop.entity.Product;
import shop.service.FeedbackService;

@RestController
@RequestMapping("/api")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

//	@GetMapping("/feedbacks")
//	public ResponseEntity<ResponseObject> getListFeedback() {
//		List<Feedback> list = feedbackService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Feedback success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/feedbacks/{id}")
	public ResponseEntity<ResponseObject> getFeedbackById(@PathVariable(value = "id") int id) {
		Feedback item = feedbackService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Feedback by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/feedbacks")
	public ResponseEntity<ResponseObject> createFeedback(@RequestBody Feedback form) {
		Feedback item = new Feedback(form.getTitle(), form.getContent(), form.getName(), form.getEmail(),
				form.getPhone(), LocalDateTime.now());
		Feedback newItem = feedbackService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Feedback success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/feedbacks/{id}")
	public ResponseEntity<ResponseObject> updateTransporter(@PathVariable(value = "id") int id,
			@RequestBody Feedback form) {
		Feedback item = feedbackService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

//		item.setIsRead(form.getIsRead());

		Feedback updateItem = feedbackService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Feedback success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/feedbacks/{id}")
	public ResponseEntity<ResponseObject> deleteTransporter(@PathVariable(value = "id") int id) {
		Feedback item = feedbackService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		feedbackService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Feedback success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/feedbacks")
	public ResponseEntity<ResponseObject> getListFeedbackPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Feedback> list = feedbackService.findAllPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll feedback by page", list);
		resposeObject.setCount(feedbackService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
//
//	@GetMapping("/feedbacks/brand/{id}")
//	public ResponseEntity<ResponseObject> getProductsByBrandPage(@PathVariable int id,
//			@RequestParam(value = "limit", required = false) int limit,
//			@RequestParam(value = "page", required = false) int page) {
//		Pageable pageable = PageRequest.of(page, limit);
//		List<Product> list = productService.findAllByBrandId(id, pageable);
////		if (listCate.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//
//		for (Product product : list) {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//					.path(product.getImage()).toUriString();
//			product.setImage(fileDownloadUri);
//		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Product by brand Id  ", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
	
	
}
