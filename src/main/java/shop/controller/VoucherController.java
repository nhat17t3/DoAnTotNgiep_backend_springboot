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

import shop.DTO.ResponseObject;
import shop.entity.Voucher;
import shop.service.VoucherService;

@RestController
@RequestMapping("/api")
public class VoucherController {

	@Autowired
	VoucherService voucherService;

//	@GetMapping("/vouchers")
//	public ResponseEntity<ResponseObject> getListVoucher() {
//		List<Voucher> list = voucherService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Voucher success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/vouchers/{id}")
	public ResponseEntity<ResponseObject> getVoucherById(@PathVariable(value = "id") int id) {
		Voucher item = voucherService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Voucher by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/vouchers")
	public ResponseEntity<ResponseObject> createVoucher(@RequestBody Voucher form) {
		Voucher item = new Voucher(form.getCode(), form.getName(), form.getType(), form.getMinOrderValue(),
				form.getMaxPrice(), form.getValue(), form.getQuantity(), form.getStartAt(), form.getEndAt(),
				form.getIsActive(), LocalDateTime.now());
		Voucher newItem = voucherService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Voucher success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/vouchers/{id}")
	public ResponseEntity<ResponseObject> updateVoucher(@PathVariable(value = "id") int id,
			@RequestBody Voucher form) {
		Voucher item = voucherService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setCode(form.getCode());
		item.setName(form.getName());
		item.setType(form.getType());
		item.setMinOrderValue(form.getMinOrderValue());
		item.setMaxPrice(form.getMaxPrice());
		item.setValue(form.getValue());
		item.setStartAt(form.getStartAt());
		item.setEndAt(form.getEndAt());
		item.setIsActive(form.getIsActive());
		item.setUpdatedAt(LocalDateTime.now());

		Voucher updateItem = voucherService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Voucher success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/vouchers/{id}")
	public ResponseEntity<ResponseObject> deleteVoucher(@PathVariable(value = "id") int id) {
		Voucher item = voucherService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		voucherService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Voucher success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/vouchers")
	public ResponseEntity<ResponseObject> getListVoucherPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Voucher> list = voucherService.findAllPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll vouchers by page", list);
		resposeObject.setCount(voucherService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/vouchers/search")
	public ResponseEntity<ResponseObject> searchVoucherByCodePage(@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Voucher> list = voucherService.searchByCode(key, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "search vouchers by code  ", list);
		resposeObject.setCount(voucherService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
