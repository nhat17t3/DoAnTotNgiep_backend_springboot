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
import shop.entity.Feedback;
import shop.service.FeedbackService;

@RestController
@RequestMapping("/api")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

	@GetMapping("/feedbacks")
	public ResponseEntity<ResponseObject> getListFeedback() {
		List<Feedback> list = feedbackService.findAll();
//		if (list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all Feedback success", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

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
				form.getPhone(), false, LocalDateTime.now());
		Feedback newItem = feedbackService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Transporter success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/feedbacks/{id}")
	public ResponseEntity<ResponseObject> updateTransporter(@PathVariable(value = "id") int id,
			@RequestBody Feedback form) {
		Feedback item = feedbackService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setIsRead(form.getIsRead());

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
