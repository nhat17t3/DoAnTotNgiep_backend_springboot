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
import shop.DTO.TransportRequest;
import shop.entity.Transport;
import shop.service.OrderService;
import shop.service.TransportService;
import shop.service.TransporterService;

@RestController
@RequestMapping("/api")
public class TransportController {
	@Autowired
	TransportService transportService;

	@Autowired
	OrderService orderService;

	@Autowired
	TransporterService transporterService;


//	@GetMapping("/transports")
//	public ResponseEntity<ResponseObject> getListOrder() {
//		List<Order> list = orderService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Order success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/transports/{id}")
	public ResponseEntity<ResponseObject> getTransportById(@PathVariable(value = "id") int id) {
		Transport item = transportService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Transport by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/transports")
	public ResponseEntity<ResponseObject> createTransport(@RequestBody TransportRequest form) {

		Transport item = new Transport();
		item.setOrder(orderService.findById(form.getOrderId()));
		item.setTransporter(transporterService.findById(form.getTransporterId()));
		item.setCode(form.getCode());
		item.setCodPrice(form.getCodPrice());
		item.setFromAddress(form.getFromAddress());
		item.setToAddress(form.getToAddress());
		item.setCreatedAt(LocalDateTime.now());
	
		Transport newItem = transportService.save(item);


		ResponseObject resposeObject = new ResponseObject("success", "create Transport success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/transports/{id}")
	public ResponseEntity<ResponseObject> updateTransport(@PathVariable(value = "id") int id,
			@RequestBody TransportRequest form) {
		Transport item = transportService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}

		item.setCode(form.getCode());
		item.setUpdatedAt(LocalDateTime.now());
		if(form.getIsDone()==true) item.setDoneAt(LocalDateTime.now());
		if (form.getIsCancle() == true) item.setCancleAt(LocalDateTime.now());
		Transport updateItem = transportService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Transport success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@DeleteMapping("/transports/{id}")
	public ResponseEntity<ResponseObject> deleteOrder(@PathVariable(value = "id") int id) {
		Transport item = transportService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		transportService.delete(id);
		ResponseObject resposeObject = new ResponseObject("success", "delete Transport success", "");
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

////
//	@GetMapping("/transports/search")
//	public ResponseEntity<ResponseObject> searchOrderByNamePage(@RequestParam(value = "q", required = true) String q,
//			@RequestParam(value = "limit", required = false) int limit,
//			@RequestParam(value = "page", required = false) int page) {
//		Pageable pageable = PageRequest.of(page, limit);
//		List<Transport> listCate = transportService.findAllByNameAndPage(q, pageable);
////		if (listCate.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "search Transport by name  ", listCate);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/transports")
	public ResponseEntity<ResponseObject> getListOrderPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Transport> listCate = transportService.findAllPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll Transport by page CREATEDAT DESC", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
