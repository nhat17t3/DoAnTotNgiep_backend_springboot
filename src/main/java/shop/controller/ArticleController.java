package shop.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import shop.DTO.ArticleRequest;
import shop.DTO.ResponseObject;
import shop.entity.Article;
import shop.entity.Brand;
import shop.entity.Product;
import shop.service.ArticleService;
import shop.service.CategoryArticleService;
import shop.service.FilesStorageService;

@RestController
@RequestMapping("/api")
public class ArticleController {

	@Autowired
	ArticleService articleService;
	
	@Autowired
	FilesStorageService storageService;

	@Autowired
	CategoryArticleService categoryArticleService;

//	@GetMapping("/articles")
//	public ResponseEntity<ResponseObject> getListArticle() {
//		List<Article> list = articleService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Article success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/articles/{id}")
	public ResponseEntity<ResponseObject> getArticleById(@PathVariable(value = "id") int id) {
		Article item = articleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		item.setView(item.getView() + 1);
		Article newitem = articleService.save(item);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(newitem.getImage()).toUriString();
		newitem.setImage(fileDownloadUri);
		
		ResponseObject resposeObject = new ResponseObject("success", "find Article by id success", newitem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/articles")
	public ResponseEntity<ResponseObject> createArticle(@RequestParam String name,@RequestParam String shortDesc,
			@RequestParam String description,@RequestParam int categoryArticleId,
			@RequestParam MultipartFile image, @RequestParam boolean isActive) throws IOException {
		Article item = new Article();
		
		item.setName(name);
//		item.setIsHot(form.getIsHot());
		item.setShortDesc(shortDesc);
		item.setDescription(description);
		item.setIsActive(isActive);
		item.setCreatedAt(LocalDateTime.now());
		item.setCategoryArticle(categoryArticleService.findById(categoryArticleId));

		try {
			String fileName;
			fileName = storageService.save(image);
			item.setImage(fileName);

		} catch (Exception e) {
			ResponseObject resposeObject = new ResponseObject("error", "error create article", e.getMessage());
			return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Article newItem = articleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Article success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/articles/{id}")
	public ResponseEntity<ResponseObject> updateArticle(@PathVariable(value = "id") int id, @RequestParam String name,@RequestParam String shortDesc,
			@RequestParam String description,@RequestParam int categoryArticleId,
			@RequestParam(required = false) MultipartFile image, @RequestParam boolean isActive) throws IOException {
		Article item = articleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		
		if (image != null) {
			try {
				String fileName;
				fileName = storageService.save(image);
				item.setImage(fileName);

			} catch (Exception e) {
				ResponseObject resposeObject = new ResponseObject("error", "error create product", e.getMessage());
				return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		item.setName(name);
//		item.setIsHot(form.getIsHot());
		item.setShortDesc(shortDesc);
		item.setDescription(description);
		item.setIsActive(isActive);
		item.setUpdatedAt(LocalDateTime.now());
		item.setCategoryArticle(categoryArticleService.findById(categoryArticleId));


		Article updateItem = articleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Article success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<ResponseObject> deleteArticle(@PathVariable(value = "id") int id) {
		Article item = articleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		articleService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Article success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/articles/search")
	public ResponseEntity<ResponseObject> searchCategoryByNamePage(@RequestParam(value = "q", required = true) String q,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Article> list = articleService.findAllByNameAndPage(q, pageable);
		
		long count = articleService.count();
		
		for (Article brand : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(brand.getImage()).toUriString();
			brand.setImage(fileDownloadUri);
		}
		
	
		

		ResponseObject resposeObject = new ResponseObject("success", "search Article by name  ", list);
		resposeObject.setCount(count);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/articles/filter")
	public ResponseEntity<ResponseObject> filterAndPage(
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(defaultValue = "default", required = false) String name,
			@RequestParam(defaultValue = "0", required = false) int categoryArticleId) {
		Pageable pageable = PageRequest.of(page, limit);
		
		List<Article> list = new ArrayList<Article>();
		
		if(name!="default" && categoryArticleId !=0) {
			list = articleService.filterAndPage(name, categoryArticleId, pageable);
		}
		else if (name!="default") list = articleService.findAllByNameAndPage(name, pageable);
		else if(categoryArticleId != 0) list = articleService.findAllByCategoryArticleId(categoryArticleId, pageable);
		else list = articleService.findAllAndPage(pageable);
		
		long count = articleService.count();
		
		for (Article brand : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(brand.getImage()).toUriString();
			brand.setImage(fileDownloadUri);
		}
		
	
		

		ResponseObject resposeObject = new ResponseObject("success", "search Article by name  ", list);
		resposeObject.setCount(count);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/articles")
	public ResponseEntity<ResponseObject> getListCategoryPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(defaultValue = "0", required = false) int categoryArticleId) {
		Pageable pageable = PageRequest.of(page, limit);
		
		List<Article> list = new ArrayList<Article>();
		
		if (categoryArticleId != 0) {
			list = articleService.findAllByCategoryArticleId(categoryArticleId, pageable);
		}
		else {
			list = articleService.findAllAndPage(pageable);
		}
		
		long count = articleService.count();
		
		for (Article brand : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(brand.getImage()).toUriString();
			brand.setImage(fileDownloadUri);
		}
		
		
		
		ResponseObject resposeObject = new ResponseObject("success", "find all Article by page", list);
		resposeObject.setCount(count);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
