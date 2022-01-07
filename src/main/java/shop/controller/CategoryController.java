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

import shop.DTO.ResponseObject;
import shop.entity.Category;
import shop.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

//	@GetMapping("/categories")
//	public ResponseEntity<ResponseObject> getListCategory() {
//		List<Category> listCate = categoryService.findAll();
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		ResponseObject resposeObject = new ResponseObject("success", "", listCate);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<ResponseObject> getCategoryById(@PathVariable(value = "id") int id) {
		Category cate = categoryService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find categoty by id sucess", cate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/categories")
	public ResponseEntity<ResponseObject> createCategory(@RequestBody Category form) {
		Category cate = new Category(form.getName(), form.getSlug(), form.getParentId(), form.getIsActive());
		cate.setCreatedAt(LocalDateTime.now());
		Category newCate = categoryService.save(cate);
		ResponseObject resposeObject = new ResponseObject("success", "create categoty sucess", newCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable(value = "id") int id,
			@RequestBody Category form) {
		Category cate = categoryService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}

		cate.setName(form.getName());
		cate.setSlug(form.getSlug());
		cate.setParentId(form.getParentId());
		cate.setIsActive(form.getIsActive());
		LocalDateTime time = LocalDateTime.now();
		cate.setUpdatedAt(time);

		Category updateCate = categoryService.save(cate);
		ResponseObject resposeObject = new ResponseObject("success", "update categoty sucess", updateCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<ResponseObject> deleteCategory(@PathVariable(value = "id") int id) {
		Category cate = categoryService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		categoryService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete categoty sucess", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/categories/search")
	public ResponseEntity<ResponseObject> searchCategoryByNamePage(@RequestParam(value = "q", required = true) String q,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Category> listCate = categoryService.findAllByNameAndPage(q, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "search category by name  ", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/categories")
	public ResponseEntity<ResponseObject> getListCategoryPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Category> listCate = categoryService.findAllAndPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all cate by page", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
