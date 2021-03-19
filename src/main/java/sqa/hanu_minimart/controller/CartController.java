package sqa.hanu_minimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api"})
public class CartController {
	@Autowired
	private CartService cartService;
	
	@GetMapping("/carts")
	List<Cart> getAll() {
		return cartService.getAllCart();
	}
	
	@GetMapping("/carts/{id}")
	Cart getOne(@PathVariable Long id) {
		return cartService.getCartById(id);
	}
	@PostMapping("/carts")
	Cart newCart(@RequestBody Cart newCart) {
		return cartService.addNewCart(newCart);
	}
	
	@DeleteMapping("/carts/{id}")
	void deleteCart(@PathVariable Long id) {
		cartService.deleteCart(id);
	}
}
