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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.ResponseObject;
import shop.entity.Brand;
import shop.service.BrandService;
import shop.service.FilesStorageService;

@RestController
@RequestMapping("/api")
//@CrossOrigin("http://localhost:8080")
public class BrandController {

//	@Value("${upload.path}")
//	private String path ;

	@Autowired
	BrandService brandService;

	@Autowired
	FilesStorageService storageService;

	@GetMapping("/brands")
	public ResponseEntity<ResponseObject> getListBrand() {
		List<Brand> listBrand = brandService.findAll();
		ResponseObject resposeObject = new ResponseObject("success", " findAll brand", listBrand);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/brands/{id}")
	public ResponseEntity<ResponseObject> getBrandById(@PathVariable(value = "id") int id) {
		Brand brand = brandService.findById(id);
		if (brand == null) {
			return ResponseEntity.notFound().build();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(brand.getImage())
				.toUriString();
		brand.setImage(fileDownloadUri);
		ResponseObject resposeObject = new ResponseObject("success", "find brand by id sucess", brand);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/brands")
	public ResponseEntity<ResponseObject> createBrand(@RequestParam String name, @RequestParam String slug,
			@RequestParam MultipartFile image, @RequestParam boolean isActive) throws IOException {

		String fileName = storageService.save(image);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName)
//				.toUriString();
		
		Brand brand = new Brand(name, slug, null, isActive);
		brand.setImage(fileName);
		brand.setCreatedAt(LocalDateTime.now());
		Brand newBrand = brandService.save(brand);
		ResponseObject resposeObject = new ResponseObject("success", "create brand sucess", newBrand);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/brands/{id}")
	public ResponseEntity<ResponseObject> updateBrand(@PathVariable(value = "id") int id, @RequestParam String name, @RequestParam String slug,
			@RequestParam MultipartFile image, @RequestParam boolean isActive) throws IOException {
		Brand brand = brandService.findById(id);
		if (brand == null) {
			return ResponseEntity.notFound().build();
		}
		
		String fileName = storageService.save(image);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(fileName)
//				.toUriString();

		brand.setName(name);
		brand.setSlug(slug);
		brand.setImage(fileName);
		brand.setIsActive(isActive);
		LocalDateTime time = LocalDateTime.now();
		brand.setUpdatedAt(time);

		Brand updateItem = brandService.save(brand);
		ResponseObject resposeObject = new ResponseObject("success", "update brand sucess", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/brands/{id}")
	public ResponseEntity<ResponseObject> deleteBrand(@PathVariable(value = "id") int id) {
		Brand cate = brandService.findById(id);
		if (cate == null) {
			return ResponseEntity.notFound().build();
		}
		brandService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete brand sucess", "");
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
