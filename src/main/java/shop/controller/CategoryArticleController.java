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
import shop.entity.CategoryArticle;
import shop.service.CategoryArticleService;

@RestController
@RequestMapping("/api")
public class CategoryArticleController {

	@Autowired
	CategoryArticleService categoryArticleService;

	@GetMapping("/cate_articles")
	public ResponseEntity<ResponseObject> getListCategoryArticle() {
		List<CategoryArticle> list = categoryArticleService.findAll();
//		if (list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all CategoryArticle success", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/cate_articles/{id}")
	public ResponseEntity<ResponseObject> getCategoryArticleById(@PathVariable(value = "id") int id) {
		CategoryArticle item = categoryArticleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find CategoryArticle by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/cate_articles")
	public ResponseEntity<ResponseObject> createCategoryArticle(@RequestBody CategoryArticle form) {
		CategoryArticle item = new CategoryArticle(form.getName(), form.getSlug(), form.getIsActive(), LocalDateTime.now());
		CategoryArticle newItem = categoryArticleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create CategoryArticle success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/cate_articles/{id}")
	public ResponseEntity<ResponseObject> updateCategoryArticle(@PathVariable(value = "id") int id,
			@RequestBody CategoryArticle form) {
		CategoryArticle item = categoryArticleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setName(form.getName());
		item.setSlug(form.getSlug());
		item.setIsActive(form.getIsActive());
		item.setUpdatedAt(LocalDateTime.now());

		CategoryArticle updateItem = categoryArticleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update CategoryArticle success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/cate_articles/{id}")
	public ResponseEntity<ResponseObject> deleteCategoryArticle(@PathVariable(value = "id") int id) {
		CategoryArticle item = categoryArticleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		categoryArticleService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete CategoryArticle success", "");
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
