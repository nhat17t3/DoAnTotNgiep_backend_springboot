package shop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.ResponseObject;
import shop.entity.Category;
import shop.entity.Product;
import shop.service.BrandService;
import shop.service.CategoryService;
import shop.service.FilesStorageService;
import shop.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	FilesStorageService storageService;

	@Autowired
	BrandService brandService;

	@Autowired
	CategoryService categotyService;

//	@GetMapping("/products")
//	public ResponseEntity<ResponseObject> getListProduct() {
//		List<Product> list = productService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Product success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseObject> getProductById(@PathVariable(value = "id") int id) {
		Product item = productService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
				.path(item.getImage()).toUriString();
		item.setImage(fileDownloadUri);
		ResponseObject resposeObject = new ResponseObject("success", "find Product by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/products")
	public ResponseEntity<ResponseObject> createProduct(@RequestParam String name,
			@RequestParam String code, @RequestParam MultipartFile image,@RequestParam MultipartFile[] moreImage, @RequestParam double unitPrice,
			@RequestParam double promotionPrice, @RequestParam int instock, @RequestParam String shortDesc,
			@RequestParam String description, @RequestParam String ingredient, @RequestParam String specification,
			@RequestParam boolean isHot, @RequestParam boolean isNew, @RequestParam boolean isActive,
			@RequestParam int brandId, @RequestParam int categoryId) {

		Product item = new Product();

		item.setName(name);
//		item.setSlug(slug);
		item.setCode(code);
		item.setUnitPrice(unitPrice);
		item.setPromotionPrice(promotionPrice);
		item.setInstock(instock);
		item.setShortDesc(shortDesc);
		item.setDescription(description);
		item.setIngredient(ingredient);
		item.setSpecification(specification);
		item.setIsHot(isHot);
		item.setIsNew(isNew);
		item.setIsActive(isActive);

		try {
			String fileName ;
			fileName = storageService.save(image);
			item.setImage(fileName);
			
		} catch (Exception e) {
			ResponseObject resposeObject = new ResponseObject("error", "error create product", e.getMessage());
			return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			List<String> fileNames = new ArrayList<>();
		      Arrays.asList(moreImage).stream().forEach(file -> {
		       String fileK = storageService.save(file);
		        fileNames.add(fileK);
		        fileNames.add(",");
		      });
		     String ooo = "";
		      for (int i = 0; i < fileNames.size() -1; i++) {
		    	  ooo = ooo.concat(fileNames.get(i));
			}
		      item.setMoreImage(ooo);
			
		} catch (Exception e) {
			ResponseObject resposeObject = new ResponseObject("error", "error create product", e.getMessage());
			return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		item.setCreatedAt(LocalDateTime.now());
		item.setBrand(brandService.findById(brandId));
		item.setCategory(categotyService.findById(categoryId));
//		Set<Category> newListCate = new HashSet<Category>();
//		for (Integer cateId : categories) {
//			Category cate = categotyService.findById(cateId);
//			newListCate.add(cate);
//		}
//		item.setCategories(newListCate);

		Product newItem = productService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Product success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/products/{id}")
	public ResponseEntity<ResponseObject> updateProduct(@PathVariable(value = "id") int id, @RequestParam String name,
			 @RequestParam String code, @RequestParam(required = false) MultipartFile image,
			@RequestParam (required = false) MultipartFile[] moreImage,
			@RequestParam double unitPrice, @RequestParam double promotionPrice, @RequestParam int instock,
			@RequestParam String shortDesc, @RequestParam String description, @RequestParam String ingredient,
			@RequestParam String specification, @RequestParam boolean isHot, @RequestParam boolean isNew,
			@RequestParam boolean isActive, @RequestParam int brandId, @RequestParam int categoryId) {
		Product item = productService.findById(id);
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
		if (moreImage != null) {
			try {
				List<String> fileNames = new ArrayList<>();
			      Arrays.asList(moreImage).stream().forEach(file -> {
			       String fileK = storageService.save(file);
			        fileNames.add(fileK);
			        fileNames.add(",");
			      });
			     String ooo = "";
			      for (int i = 0; i < fileNames.size() -1; i++) {
			    	  ooo = ooo.concat(fileNames.get(i));
				}
			      item.setMoreImage(ooo);
				
			} catch (Exception e) {
				ResponseObject resposeObject = new ResponseObject("error", "error create product", e.getMessage());
				return new ResponseEntity<>(resposeObject, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		item.setName(name);
//		item.setSlug(slug);
		item.setCode(code);
		item.setUnitPrice(unitPrice);
		item.setPromotionPrice(promotionPrice);
		item.setInstock(instock);
		item.setShortDesc(shortDesc);
		item.setDescription(description);
		item.setIngredient(ingredient);
		item.setSpecification(specification);
		item.setIsHot(isHot);
		item.setIsNew(isNew);
		item.setIsActive(isActive);
		item.setBrand(brandService.findById(brandId));
		item.setCategory(categotyService.findById(categoryId));
		item.setUpdatedAt(LocalDateTime.now());

//		Set<Category> newListCate = new HashSet<Category>();
//		for (Integer cateId : categories) {
//			Category cate = categotyService.findById(cateId);
//			newListCate.add(cate);
//		}
//
//		item.setCategories(newListCate);

		Product updateItem = productService.save(item);
		System.out.println(updateItem.getImage());
		ResponseObject resposeObject = new ResponseObject("success", "update Product success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseObject> deleteProduct(@PathVariable(value = "id") int id) {
		Product item = productService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		productService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Product success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/products/search")
	public ResponseEntity<ResponseObject> searchProductByNamePage(
			@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Product> list = productService.searchByNameOrCode(key, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}

		for (Product product : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(product.getImage()).toUriString();
			product.setImage(fileDownloadUri);
		}
		ResponseObject resposeObject = new ResponseObject("success", "search Product by name  ", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/products/brand/{id}")
	public ResponseEntity<ResponseObject> getProductsByBrandPage(@PathVariable int id,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Product> list = productService.findAllByBrandId(id, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}

		for (Product product : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(product.getImage()).toUriString();
			product.setImage(fileDownloadUri);
		}
		ResponseObject resposeObject = new ResponseObject("success", "find all Product by brand Id  ", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/products/category/{id}")
	public ResponseEntity<ResponseObject> getProductsByCategoryPage(@PathVariable int id,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Product> list = productService.findAllByCategorieId(id, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}

		for (Product product : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(product.getImage()).toUriString();
			product.setImage(fileDownloadUri);
		}
		ResponseObject resposeObject = new ResponseObject("success", "find all Product by categoryID  ", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<ResponseObject> getListProductPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "sortBy", required = false) String sortBy) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Product> list = new ArrayList<Product>();
		switch (sortBy) {
		case "createdAtDESC":
			list = productService.findAllCreatedAtDESC(pageable);
			break;
		case "PriceASC":
			list = productService.findAllPriceASC(pageable);
			break;
		case "PriceDESC":
			list = productService.findAllPriceDESC(pageable);
			break;
		default:
			list = productService.findAllPage(pageable);
			break;
		}
		

//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		
//		List<Product> kkk = productService.findAll();
//		long count = 0L;
//		for (Product product : kkk) {
//			count ++;
//		}
		
		long count = productService.count();
		
		for (Product product : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(product.getImage()).toUriString();
			product.setImage(fileDownloadUri);
			
//			String more = "";
//			for (String image : product.getImage().split(",")) {
//				String fileDownloadUri1 = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//						.path(image).toUriString();
//				more = more.concat(fileDownloadUri1).concat(",");
//			}
//			product.setMoreImage(more.substring(0, more.length()-1));
		}
		
		
		ResponseObject resposeObject = new ResponseObject("success", "find all Product by page", list);
		
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuu" + list);
		resposeObject.setCount(count);
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuu" + list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
