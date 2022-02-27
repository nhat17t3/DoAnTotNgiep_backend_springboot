package shop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import shop.DTO.ArticleRequest;
import shop.DTO.ResponseObject;
import shop.entity.Article;
import shop.service.ArticleService;
import shop.service.CategoryArticleService;

@RestController
@RequestMapping("/api")
public class ArticleController {

	@Autowired
	ArticleService articleService;

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
		
		ResponseObject resposeObject = new ResponseObject("success", "find Article by id success", newitem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/articles")
	public ResponseEntity<ResponseObject> createArticle(@RequestBody ArticleRequest form) {
		Article item = new Article(form.getName(), form.getSlug(), form.getIsHot(), form.getShortDesc(),
				form.getDescription(), form.getIsActive(), LocalDateTime.now());

		item.setCategoryArticle(categoryArticleService.findById(form.getCategoryArticleId()));

		Article newItem = articleService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Article success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/articles/{id}")
	public ResponseEntity<ResponseObject> updateArticle(@PathVariable(value = "id") int id, @RequestBody ArticleRequest form) {
		Article item = articleService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setName(form.getName());
		item.setSlug(form.getSlug());
		item.setIsHot(form.getIsHot());
		item.setShortDesc(form.getShortDesc());
		item.setDescription(form.getDescription());
		item.setIsActive(form.getIsActive());
		item.setUpdatedAt(LocalDateTime.now());
		item.setCategoryArticle(categoryArticleService.findById(form.getCategoryArticleId()));

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

		ResponseObject resposeObject = new ResponseObject("success", "search Article by name  ", list);
		resposeObject.setCount(articleService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/articles")
	public ResponseEntity<ResponseObject> getListCategoryPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Article> list = articleService.findAllAndPage(pageable);
		ResponseObject resposeObject = new ResponseObject("success", "find all Article by page", list);
		resposeObject.setCount(articleService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
