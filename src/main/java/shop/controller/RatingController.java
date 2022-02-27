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

import shop.DTO.RatingRequest;
import shop.DTO.ResponseObject;
import shop.entity.Rating;
import shop.entity.UserProductKey;
import shop.entity.Voucher;
import shop.service.ProductService;
import shop.service.RatingService;
import shop.service.UserService;

@RestController
@RequestMapping("/api")
public class RatingController {

	@Autowired
	RatingService ratingService;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

//	@GetMapping("/ratings")
//	public ResponseEntity<ResponseObject> getListRating() {
//		List<Rating> list = ratingService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Rating success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/ratings/id")
	public ResponseEntity<ResponseObject> getRatingById(@RequestParam int userId, @RequestParam int productId) {
		UserProductKey key = new UserProductKey(userId, productId);

		Rating item = ratingService.findById(key);

		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Rating by key(userId,productId) success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/ratings")
	public ResponseEntity<ResponseObject> createRating(@RequestBody RatingRequest form) {
		Rating item = new Rating();

		item.setUser(userService.findById(form.getUserId()));
		item.setProduct(productService.findById(form.getProductId()));
		item.setStar(form.getStar());
		item.setContent(form.getContent());
		item.setCreatedAt(LocalDateTime.now());
		Rating newItem = ratingService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create rating success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/ratings")
	public ResponseEntity<ResponseObject> updateRating(@RequestBody RatingRequest form) {
		UserProductKey key = new UserProductKey(form.getUserId(),form.getProductId());
		Rating item = ratingService.findById(key);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setStar(form.getStar());
		item.setContent(form.getContent());
		item.setUpdatedAt(LocalDateTime.now());

		Rating updateItem = ratingService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Rating success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/ratings")
	public ResponseEntity<ResponseObject> deleteRating(@RequestParam int userId, @RequestParam int productId) {
		UserProductKey key = new UserProductKey(userId, productId);
		Rating item = ratingService.findById(key);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ratingService.delete(key);
		ResponseObject resposeObject = new ResponseObject("success", "delete Rating success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	@GetMapping("/ratings")
	public ResponseEntity<ResponseObject> getListRatingPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Rating> list = ratingService.findAllPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll Rating by page", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
//
	@GetMapping("/ratings/product/{id}")
	public ResponseEntity<ResponseObject> getListRatingByProductId(@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page , @PathVariable int id) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Rating> listCate = ratingService.findAllByProductId(id, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all Rating by page", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
