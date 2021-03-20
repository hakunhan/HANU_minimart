package sqa.hanu_minimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.Order;

import sqa.hanu_minimart.service.OrderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/orders"})
public class OrderController {
	@Autowired
	private OrderService orderService;
	@GetMapping
	public List<Order> getAll(){
		return orderService.getAllOrder();
	}
	@GetMapping("/{id}")
	public Order getById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}
	@PostMapping(path = "/{id}")
	public Order newOrder(@RequestBody Cart cart, @PathVariable Long id) {
		return orderService.addNewOrder(cart, id);
	}
	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	@PutMapping("/{id}")
	public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
		orderService.updateOrder(order, id);
	}
	
}
