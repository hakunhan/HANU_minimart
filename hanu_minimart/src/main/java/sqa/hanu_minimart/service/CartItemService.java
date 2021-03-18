package sqa.hanu_minimart.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.repository.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	public CartService	cartService;
	
	public List<CartItem> getAllItem(){
		return cartItemRepository.findAll();
	}
	public CartItem getById(Long id) {
		return cartItemRepository.findById(id).get();
	}
	public CartItem addNewItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	public void deleteItem(Long id) {
		if(!cartItemRepository.existsById(id)) {
			throw new IllegalStateException("Item is not exits");
		}
		cartItemRepository.deleteById(id);
	}
	public void deleteAll() {
		cartItemRepository.deleteAll();
	}
	@Transactional
	public void updateItem(Long id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Item does not exist!"));
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}
}
