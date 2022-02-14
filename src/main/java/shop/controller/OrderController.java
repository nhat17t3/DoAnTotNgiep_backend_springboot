package shop.controller;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shop.DTO.OrderDetailRequest;
import shop.DTO.OrderRequest;
import shop.DTO.ResponseObject;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.service.OrderDetailService;
import shop.service.OrderService;
import shop.service.PaymentService;
import shop.service.ProductService;
import shop.service.UserService;
import shop.service.VoucherService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	VoucherService voucherService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderDetailService orderDetailService;

//	@GetMapping("/vouchers")
//	public ResponseEntity<ResponseObject> getListOrder() {
//		List<Order> list = orderService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Order success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<ResponseObject> getOrderById(@PathVariable(value = "id") int id) {
		Order item = orderService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseObject resposeObject = new ResponseObject("success", "find Order by id success", item);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@PostMapping("/orders")
	public ResponseEntity<ResponseObject> createOrder(@RequestBody OrderRequest form) {

		Order item = new Order();
		item.setUser(userService.findById(form.getUserId()));
		item.setPayment(paymentService.findById(form.getPaymentId()));
		item.setVoucher(voucherService.findById(form.getVoucherId()));
		item.setNameReceiver(form.getNameReceiver());
		item.setPhoneReceiver(form.getPhoneReceiver());
		item.setAddressReceiver(form.getAddressReceiver());
		item.setTotal(form.getTotal());
		item.setDiscount(form.getDiscount());
		item.setShippingFee(form.getShippingFee());
		item.setNote(form.getNote());
		item.setCreatedAt(LocalDateTime.now());
		;

		Order newItem = orderService.save(item);

		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		Set<OrderDetailRequest> orderDetailRequests = form.getOrderDetailRequests();
		for (OrderDetailRequest orderDetailRequest : orderDetailRequests) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(orderService.findById(newItem.getId()));
			orderDetail.setProduct(productService.findById(orderDetailRequest.getProductId()));
			orderDetail.setQuantity(orderDetailRequest.getQuantity());
			orderDetail.setUnitPrice(orderDetailRequest.getUnitPrice());
			orderDetail.setPromotionPrice(orderDetailRequest.getPromotionPrice());
			orderDetailService.save(orderDetail);
			orderDetails.add(orderDetail);
		}

//		item.setOrderDetails(orderDetails);

		ResponseObject resposeObject = new ResponseObject("success", "create Order success", newItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.CREATED);
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<ResponseObject> updateOrder(@PathVariable(value = "id") int id,
			@RequestBody OrderRequest form) {
		Order item = orderService.findById(id);
		if (item == null) {
			return ResponseEntity.notFound().build();
		}


		if(form.getIsConfirm()== true) item.setConfirmAt(LocalDateTime.now());
		if(form.getIsPay() == true) item.setPayAt(LocalDateTime.now());
		if(form.getIsCancle()== true) item.setCancleAt(LocalDateTime.now());

		Order updateItem = orderService.save(item);
		ResponseObject resposeObject = new ResponseObject("success", "update Order success", updateItem);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	

	
//	@DeleteMapping("/orders/{id}")
//	public ResponseEntity<ResponseObject> deleteOrder(@PathVariable(value = "id") int id) {
//		Order item = orderService.findById(id);
//		if (item == null) {
//			return ResponseEntity.notFound().build();
//		}
//		orderService.delete(id);
//		ResponseObject resposeObject = new ResponseObject("success", "delete Order success", "");
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}
//
	@GetMapping("/orders/search")
	public ResponseEntity<ResponseObject> searchOrderByNamePage(@RequestParam(value = "key", required = true) String key,
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Order> listCate = orderService.findAllByNameOrPhoneReceiver(key, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "search Order by name  ", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}

	@GetMapping("/orders")
	public ResponseEntity<ResponseObject> getListOrderPage(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Order> listCate = orderService.findAllPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll Order by page", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/orders/user/{id}")
	public ResponseEntity<ResponseObject> getListOrderByUserId(@PathVariable int id) {
		Pageable pageable = PageRequest.of(0, 1000);
		List<Order> listCate = orderService.findAllByUserId(id, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll Order by User ID", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/orders/filter")
	public ResponseEntity<ResponseObject> getListOrderByStatus(@RequestParam(value = "limit", required = true) int limit,
			@RequestParam(value = "page", required = true) int page, @RequestParam int status) {
		Pageable pageable = PageRequest.of(page, limit);
		List<Order> listCate = orderService.findAllByStatus(status, pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "findAll Order by Status Order", listCate);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
