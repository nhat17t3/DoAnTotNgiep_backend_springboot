package shop.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.ResponseObject;
import shop.entity.Brand;
import shop.entity.Category;
import shop.service.CategoryService;
import shop.service.FilesStorageService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FilesStorageService storageService;

	@GetMapping("/categories")
	public ResponseEntity<ResponseObject> getListCategory() {
		List<Category> listCate = categoryService.findAll();
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		for (Category cate : listCate) {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//					.path(cate.getImage()).toUriString();
//			cate.setImage(fileDownloadUri);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all category success", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<ResponseObject> getCategoryById(@PathVariable(value = "id") int id) {
		Category cate = categoryService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//				.path(cate.getImage()).toUriString();
//		cate.setImage(fileDownloadUri);
		ResponseObject resposeObject = new ResponseObject("success", "find categoty by id success", cate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/categories")
	public ResponseEntity<ResponseObject> createCategory(@RequestBody Category form) throws IOException {
		
		
		Category cate = new Category();
		cate.setName(form.getName());
//		cate.setSlug(form.getSlug());
		cate.setParentId(form.getParentId());
		cate.setIsActive(form.getIsActive());
		cate.setCreatedAt(LocalDateTime.now());
//		try {
//			String fileName;
//			fileName = storageService.save(image);
//			cate.setImage(fileName);
//
//		} catch (Exception e) {
//			ResponseObject resposeObject = new ResponseObject("error", "error create brand", e.getMessage());
//			return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		Category newCate = categoryService.save(cate);
		ResponseObject resposeObject = new ResponseObject("success", "create categoty success", newCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/categories/{id}")
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable(value = "id") int id, @RequestBody Category form) throws IOException {
		Category cate = categoryService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}

		cate.setName(form.getName());
//		cate.setSlug(form.getSlug());
		cate.setParentId(form.getParentId());
		cate.setIsActive(form.getIsActive());
		LocalDateTime time = LocalDateTime.now();
		cate.setUpdatedAt(time);
		
//		if (image != null) {
//			try {
//				String fileName;
//				fileName = storageService.save(image);
//				cate.setImage(fileName);
//
//			} catch (Exception e) {
//				ResponseObject resposeObject = new ResponseObject("error", "error create brand", e.getMessage());
//				return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		}

		Category updateCate = categoryService.save(cate);
		ResponseObject resposeObject = new ResponseObject("success", "update categoty success", updateCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<ResponseObject> deleteCategory(@PathVariable(value = "id") int id) {
		Category cate = categoryService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		categoryService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete categoty success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

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
