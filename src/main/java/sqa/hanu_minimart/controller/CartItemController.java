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

import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.service.CartItemService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api"})
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/cartItems")
	List<CartItem> getAll() {
		return cartItemService.getAllItem();
	}
	@GetMapping("/cartItems/{id}")
	CartItem getById(@PathVariable Long id) {
		return cartItemService.getById(id);
	}
	@PostMapping("/cartItems")
	CartItem addNewItem(@RequestBody CartItem newItem) {
		return cartItemService.addNewItem(newItem);
	}
	@PutMapping("cartItems/{id}")
	void updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
		cartItemService.updateItem(id, quantity);
	}
//	@DeleteMapping("/cartItems")
//	void deleteAllCartItem() {
//		cartItemService.deleteAll();
//	}
	@DeleteMapping("/cartItems/{id}")
	void deleteCartItem(@PathVariable Long id) {
		cartItemService.deleteItem(id);
	}
}