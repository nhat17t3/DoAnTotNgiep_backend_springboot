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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.FavouriteRequest;
import shop.DTO.ResponseObject;
import shop.entity.Favourite;
import shop.entity.Product;
import shop.entity.UserProductKey;
import shop.service.FavouriteService;
import shop.service.ProductService;
import shop.service.UserService;

@RestController
@RequestMapping("/api")
public class FavouriteController {

	@Autowired
	FavouriteService favouriteService;
	
	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@GetMapping("/favourites")
	public ResponseEntity<ResponseObject> getListFavourite() {
		List<Favourite> list = favouriteService.findAll();
//		if (list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		for (Favourite a : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(a.getProduct().getImage()).toUriString();
			a.getProduct().setImage(fileDownloadUri);
		}
		System.out.println("aaaaaaaaaaaaaaaaaaaa" +list );
		ResponseObject resposeObject = new ResponseObject("success", "find all Favourite success", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

//	@GetMapping("/ratings/{id}")
//	public ResponseEntity<ResponseObject> getRatingById(@PathVariable(value = "id") int id) {
//		Voucher item = ratingService.findById(id);
//		if (item == null) {
//			return ResponseEntity.notFound().build();
//		}
//		ResponseObject resposeObject = new ResponseObject("success", "find Voucher by id success", item);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@PostMapping("/favourites")
	public ResponseEntity<ResponseObject> createFavourite(@RequestBody FavouriteRequest form) {
		Favourite item = new Favourite();
		item.setUser(userService.findById(form.getUserId()));
		item.setProduct(productService.findById(form.getProductId()));
		item.setDateFavourite(LocalDateTime.now());
		
		Favourite newItem = favouriteService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Favourite success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@DeleteMapping("/favourites")
	public ResponseEntity<ResponseObject> deleteFavourite(@RequestBody FavouriteRequest form) {
		UserProductKey key = new UserProductKey(form.getUserId(),form.getProductId());
		Favourite item = favouriteService.findById(key);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		favouriteService.delete(key);
		ResponseObject resposeObject = new ResponseObject("success", "delete Favourite success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
//	@PutMapping("/ratings/{id}")
	
	
//	public ResponseEntity<ResponseObject> updateVoucher(@PathVariable(value = "id") int id,
//			@RequestBody Voucher form) {
//		Voucher item = ratingService.findById(id);
//		if (item == null) {
//			return ResponseEntity.notFound().build();
//		}
//
//		item.setCode(form.getCode());
//		item.setName(form.getName());
//		item.setType(form.getType());
//		item.setMinOrderValue(form.getMinOrderValue());
//		item.setMaxPrice(form.getMaxPrice());
//		item.setValue(form.getValue());
//		item.setStartAt(form.getStartAt());
//		item.setEndAt(form.getEndAt());
//		item.setIsActive(form.getIsActive());
//		item.setUpdatedAt(LocalDateTime.now());
//
//		Voucher updateItem = ratingService.save(item);
//		ResponseObject resposeObject = new ResponseObject("success", "update Voucher success", updateItem);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

//
//	@GetMapping("/ratings/search")
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
	@GetMapping("/favourites/user/{id}")
	public ResponseEntity<ResponseObject> getListFavourite(@PathVariable int id) {
		
		List<Favourite> listCate = favouriteService.findAllByUserId(id);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		
		for (Favourite a : listCate) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(a.getProduct().getImage()).toUriString();
			a.getProduct().setImage(fileDownloadUri);
		}
		ResponseObject resposeObject = new ResponseObject("success", "find all favourite by userID", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
