package shop.controller;

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
import org.springframework.web.bind.annotation.RestController;

import shop.DTO.ResponseObject;
import shop.entity.Delivery;
import shop.service.DeliveryService;

@RestController
@RequestMapping("/api")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;

	@GetMapping("/deliveries")
	public ResponseEntity<ResponseObject> getListDelivery() {
		List<Delivery> list = deliveryService.findAll();
//		if (list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "find all Delivery success", list);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/deliveries/{id}")
	public ResponseEntity<ResponseObject> getDeliveryById(@PathVariable(value = "id") int id) {
		Delivery item = deliveryService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Delivery by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/deliveries")
	public ResponseEntity<ResponseObject> createDelivery(@RequestBody Delivery form) {
		Delivery item = new Delivery(form.getName(), form.getDescription(),form.getFee(), form.getIsActive(), LocalDateTime.now());
		Delivery newItem = deliveryService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "create Delivery success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/deliveries/{id}")
	public ResponseEntity<ResponseObject> updateDelivery(@PathVariable(value = "id") int id,
			@RequestBody Delivery form) {
		Delivery item = deliveryService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setName(form.getName());
		item.setDescription(form.getDescription());
		item.setFee(form.getFee());
		item.setIsActive(form.getIsActive());
		item.setUpdatedAt(LocalDateTime.now());

		Delivery updateItem = deliveryService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Delivery success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deliveries/{id}")
	public ResponseEntity<ResponseObject> deleteDelivery(@PathVariable(value = "id") int id) {
		Delivery item = deliveryService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		deliveryService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Delivery success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
