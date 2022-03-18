package shop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import shop.DTO.OrderDetailRequest;
import shop.DTO.OrderRequest;
import shop.DTO.ResponseObject;
import shop.entity.Article;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.Product;
import shop.entity.Voucher;
import shop.service.OrderDetailService;
import shop.service.OrderService;
import shop.service.PaymentService;
//import shop.service.PaymentService;
import shop.service.ProductService;
import shop.service.UserService;
//import shop.service.VoucherService;
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
//		item.setPaymentType(form.getPaymentType());
//		item.setVoucher(voucherService.findById(form.getVoucherId()));
		item.setNameReceiver(form.getNameReceiver());
		item.setPhoneReceiver(form.getPhoneReceiver());
		item.setAddressReceiver(form.getAddressReceiver());
		item.setEmailReceiver(form.getEmailReceiver());
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
		
		if(form.getVoucherId()!= 0) {
			Voucher voucher =voucherService.findById(form.getVoucherId());
			voucher.setQuantity(voucher.getQuantity()-1);
			Voucher save = voucherService.save(voucher);
		}
		
//		for (OrderDetail itemOrder : newItem.getOrderDetails()) {
//			Product product = productService.findById(itemOrder.getProduct().getId())  ;
//			product.setInstock(product.getInstock()-itemOrder.getQuantity());
//			Product save1 = productService.save(product);
//		}

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
		if(form.getIsPay() == true) item.setPaymentStatus(true);
		if(form.getIsDone() == true) item.setDoneAt(LocalDateTime.now());
		if(form.getIsCancle()== true) item.setCancleAt(LocalDateTime.now());
		if(form.getCancleReason() != null) item.setCancleReason(form.getCancleReason());
		if(form.getStatus() != null) item.setStatus(form.getStatus());;

		Order updateItem = orderService.save(item);
		
		if(updateItem.getStatus()==1) {
			for (OrderDetail itemOrder : updateItem.getOrderDetails()) {
				Product product = productService.findById(itemOrder.getProduct().getId())  ;
				product.setInstock(product.getInstock()-1);
				Product save = productService.save(product);
			}
		}
			
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
		resposeObject.setCount(orderService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/orders/filter1")
	public ResponseEntity<ResponseObject> filterAndPage(
			@RequestParam(value = "limit", required = false) int limit,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(defaultValue = "default", required = false) String key,
			@RequestParam(defaultValue = "0", required = false) int status) {
		Pageable pageable = PageRequest.of(page, limit);
		
		List<Order> list = new ArrayList<Order>();
		if(key!="default" && status !=0) {
			list = orderService.filterAndPage(key, status, pageable);
		}
		else if (key!="default") list = orderService.findAllByNameOrPhoneReceiver(key, pageable);
		else if(status != 0) list = orderService.findAllByStatus(status, pageable);
		else list= orderService.findAllPage(pageable);
//		if (listCate.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
		ResponseObject resposeObject = new ResponseObject("success", "search Order by name  ", list);
		resposeObject.setCount(orderService.count());
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
		resposeObject.setCount(orderService.count());
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
		resposeObject.setCount(orderService.count());
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/cart/update")
	public ResponseEntity<ResponseObject> getCart( @RequestParam Set<Integer> products) {
		
		
		Set<Product> newList = new HashSet<Product>();
		for (Integer itemId : products) {
			Product product = productService.findById(itemId);
			if(product != null) newList.add(product);
		}
		
		for (Product product : newList) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(product.getImage()).toUriString();
			product.setImage(fileDownloadUri);

			String moreImage = "";
			for (String image : product.getMoreImage().split(",")) {
				String fileDownloadUri1 = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
						.path(image).toUriString();
				System.out.println(fileDownloadUri1);
				moreImage = moreImage.concat(fileDownloadUri1).concat(",");
			}
			product.setMoreImage(moreImage.substring(0, moreImage.length() - 1));
		}
		
		
		ResponseObject resposeObject = new ResponseObject("success", "get cart", newList);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
}
