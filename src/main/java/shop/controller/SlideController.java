package shop.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.ResponseObject;
import shop.entity.Slide;
import shop.service.FilesStorageService;
import shop.service.SlideService;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:8080")
public class SlideController {

//	@Value("${upload.path}")
//	private String path ;

	@Autowired
	SlideService slideService;

	@Autowired
	FilesStorageService storageService;

	@GetMapping("/slides")
	public ResponseEntity<ResponseObject> getListSlide() {
		List<Slide> list = slideService.findAll();
		for (Slide slide : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(slide.getImage())
					.toUriString();
			slide.setImage(fileDownloadUri);
		}
		ResponseObject resposeObject = new ResponseObject("success", " findAll slide", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/slides/{id}")
	public ResponseEntity<ResponseObject> getSlideById(@PathVariable(value = "id") int id) {
		Slide slide = slideService.findById(id);
		if (slide == null) {
			return ResponseEntity.notFound().build();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(slide.getImage())
				.toUriString();
		slide.setImage(fileDownloadUri);
		ResponseObject resposeObject = new ResponseObject("success", "find slide by id success", slide);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/slides")
	public ResponseEntity<ResponseObject> createSlide(@RequestParam String name, 
			@RequestParam MultipartFile image,@RequestParam int sort,@RequestParam String link, @RequestParam boolean isActive) throws IOException {

		String fileName ;
		try {
			fileName = storageService.save(image);
			
		} catch (Exception e) {
			ResponseObject resposeObject = new ResponseObject("error", "error create slide", e.getMessage());
			return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName)
//				.toUriString();
		
		Slide slide = new Slide();

		slide.setName(name);
		slide.setImage(fileName);
		slide.setSort(sort);
		slide.setLink(link);
		slide.setIsActive(isActive);
		slide.setCreatedAt(LocalDateTime.now());
		
		Slide newSlide = slideService.save(slide);
		ResponseObject resposeObject = new ResponseObject("success", "create slide success", newSlide);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/slides/{id}")
	public ResponseEntity<ResponseObject> updateSlide(@PathVariable(value = "id") int id, @RequestParam String name, 
			@RequestParam MultipartFile image,@RequestParam int sort,@RequestParam String link, @RequestParam boolean isActive) throws IOException {
		Slide slide = slideService.findById(id);
		if (slide == null) {
			return ResponseEntity.notFound().build();
		}
		
		String fileName ;
		try {
			fileName = storageService.save(image);
			
		} catch (Exception e) {
			ResponseObject resposeObject = new ResponseObject("error", "error create slide", e.getMessage());
			return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName)
//				.toUriString();

		slide.setName(name);
		slide.setImage(fileName);
		slide.setSort(sort);
		slide.setLink(link);
		slide.setIsActive(isActive);
		LocalDateTime time = LocalDateTime.now();
		slide.setUpdatedAt(time);

		Slide updateItem = slideService.save(slide);
		ResponseObject resposeObject = new ResponseObject("success", "update Slide success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/slides/{id}")
	public ResponseEntity<ResponseObject> deleteSlide(@PathVariable(value = "id") int id) {
		Slide slide = slideService.findById(id);
		if (slide == null) {
			return ResponseEntity.notFound().build();
		}
		slideService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete slide success","");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

//	@GetMapping("/categories/search")
//	public ResponseEntity<ResponseObject> searchCategoryByNamePage(@RequestParam(value = "q", required = true) String q,
//			@RequestParam(value = "limit", required = false) int limit,
//			@RequestParam(value = "page", required = false) int page) {
//		Pageable pageable = PageRequest.of(page, limit);
//		List<Category> listCate = categoryService.findAllByNameAndPage(q, pageable);
//		ResponseObject resposeObject = new ResponseObject("success", "search category by name  ", listCate);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

}
