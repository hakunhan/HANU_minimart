package sqa.hanu_minimart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.repository.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	public CartItemService() {
		
	}
	
	public List<CartItem> getAllItem(){
		return cartItemRepository.findAll();
	}
	public CartItem getById(int id) {
		return cartItemRepository.findById(id).get();
	}

	/*  TODO: sửa lại tạo order line với việc nhận Card ID và tìm Cart tương ứng trong db
	 *           nếu không thấy trong db trả lại exception
	 */
	public CartItem addNewItem(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	public void deleteItem(int id) {
		if(!cartItemRepository.existsById(id)) {
			throw new IllegalStateException("Item is not exits");
		}
		cartItemRepository.deleteById(id);
	}
	public void deleteAll() {
		cartItemRepository.deleteAll();
	}
	public void updateItem(int id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Item does not exist!"));
		cartItem.setQuantity(quantity);
	}
}
