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

import shop.DTO.OrderDetailRequest;
import shop.DTO.OrderRequest;
import shop.DTO.ResponseObject;
import shop.DTO.ResponseStatistics;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.service.OrderDetailService;
import shop.service.OrderService;
import shop.service.PaymentService;
//import shop.service.PaymentService;
import shop.service.ProductService;
import shop.service.UserService;
//import shop.service.VoucherService;

@RestController
@RequestMapping("/api")
public class RevenueController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	PaymentService paymentService;

//	@Autowired
//	VoucherService voucherService;

	@Autowired
	ProductService productService;

//	@GetMapping("/vouchers")
//	public ResponseEntity<ResponseObject> getListOrder() {
//		List<Order> list = orderService.findAll();
////		if (list.isEmpty()) {
////			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		}
//		ResponseObject resposeObject = new ResponseObject("success", "find all Order success", list);
//		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
//	}

	@GetMapping("/statistics/count")
	public ResponseEntity<ResponseObject> countElement() {
		
		ResponseStatistics a = new ResponseStatistics();
		a.setTotalUser(userService.count());
		a.setTotalProduct(productService.count());
		a.setTotalOrder(orderService.count());
		Double totalRevenue = (double) 0;
		List<Order> listOrder = orderService.findAll();
		System.out.println("bbbbbbb");
		for (Order order : listOrder) {
			if (order.getStatus()==3) totalRevenue += order.getTotal();	
			System.out.println("ccccccccc");
		}
		
		
		System.out.println("oooooo");
		a.setTotalRevenue(totalRevenue);
		System.out.println("kkkkkkk");
		
		
		
		
		ResponseObject resposeObject = new ResponseObject("success", "thong ke so lieu phan tu", a);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}
	
	@GetMapping("/statistics/revenue")
	public ResponseEntity<ResponseObject> revenueByMonth( @RequestParam int year) {
		
		ResponseStatistics a = new ResponseStatistics();
		a.setTotalUser(userService.count());
		a.setTotalProduct(productService.count());
		a.setTotalOrder(orderService.count());
		double totalRevenue = 0;
		List<Order> listOrder = orderService.findAll();
		for (Order order : listOrder) {
			if (order.getStatus()==3) totalRevenue += order.getTotal();				
		}
		a.setTotalRevenue(totalRevenue);
		////////////////////////////
		
		List<Double> b = new ArrayList<Double>();
		for (int i = 1; i <= 12 ; i++) {
			int day = 31;
			if(i == 2) day = 28;
			if( i == 4 || i== 6 || i==9 || i== 11) day = 30;
			
			LocalDateTime start = LocalDateTime.of(year, i, 1, 1, 1);
			LocalDateTime end = LocalDateTime.of(year, i, day, 1, 1);
			double totalMonth = 0;
			for (Order order : listOrder) {
				if (order.getStatus()==3 && order.getCreatedAt().isBefore(end) && order.getCreatedAt().isAfter(start)) 
					totalMonth += order.getTotal();				
			}
			b.add(totalMonth);
		}
		
		a.setRevenueByMonthly(b);
		
		ResponseObject resposeObject = new ResponseObject("success", "thong ke so lieu phan tu", a);
		return new ResponseEntity<>(resposeObject, HttpStatus.OK);
	}







	

	
	
}
