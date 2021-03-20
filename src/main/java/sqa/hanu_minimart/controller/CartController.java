package sqa.hanu_minimart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api"})
public class CartController {
	@Autowired
	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/carts")
	List<Cart> getAll() {
		return cartService.getAllCart();
	}

	@GetMapping("/carts/getByUser")
	Cart getByUser(@RequestParam Long userId){
		return cartService.getByUserId(userId);
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
