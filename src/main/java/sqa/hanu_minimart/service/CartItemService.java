package sqa.hanu_minimart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.payload.CartItemPayLoad;
import sqa.hanu_minimart.repository.CartItemRepository;
import sqa.hanu_minimart.repository.CartRepository;

import javax.transaction.Transactional;

@Service
public class CartItemService {

	@Autowired
	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final ProductService productService;

	public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository, ProductService productService) {
		this.cartItemRepository = cartItemRepository;
		this.cartRepository = cartRepository;
		this.productService = productService;
	}

	public List<CartItem> getAllItem(){
		return cartItemRepository.findAll();
	}

	public CartItem getById(Long id) {
		return cartItemRepository.findById(id).get();
	}

	/*  TODO: sửa lại tạo order line với việc nhận Card ID và tìm Cart tương ứng trong db
	 *           nếu không thấy trong db trả lại exception
	 */
	public CartItem addNewItem(CartItemPayLoad cartItemPayLoad) {
		Cart cart = cartRepository.findCartById(cartItemPayLoad.getCartId());

		CartItem cartItem = new CartItem(cart, cartItemPayLoad.getProductName(), cartItemPayLoad.getQuantity(), cartItemPayLoad.getContent());
		cart.getCartItem().add(cartItem);

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

	public void deleteByCartId(Long cartId) {
		cartItemRepository.deleteAllByCart_Id(cartId);
	}

	@Transactional
	public void updateItem(Long id, int quantity) {
		CartItem cartItem = cartItemRepository.findById(id)
				.orElseThrow(() -> new IllegalStateException("Item does not exist!"));
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}
}
