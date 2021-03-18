package sqa.hanu_minimart.service;


import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.User;
import sqa.hanu_minimart.repository.CartRepository;
import sqa.hanu_minimart.repository.OrderRepository;
import sqa.hanu_minimart.security.UserPrincipal;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderLineService orderLineService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private CartItemService cartItemSevice;
	@Autowired
	private CartRepository cartRepository;
	public OrderService() {}
	
	public List<Order> getAllOrder(){
		return orderRepository.findAll();
	}
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}
	public Order addNewOrder(Cart cart) {
		UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) accountService.getAllAccount(principal.getId(), null, null, null, null, 0, 0, null);
		
		Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 72);      // adds 72 hour
        cal.getTime();                         // returns new date object plus 72 hour
		
		Order order = new Order(user, null, cal.getTime(), new Date(), "pending", 0);
		
        Set<OrderLine> orderLine = new HashSet<>();
		Set<CartItem> cartItem = cart.getCartItem();
		float total = 0;
		for(CartItem item:cartItem) {
			String productName = item.getProduct().getName();
			int quantity = item.getQuantity();
			total += quantity*item.getProduct().getPrice();
			orderLine.add(new OrderLine(order, item.getProduct(), quantity));
			orderLineService.addnewOrderItem(new OrderLine(order, item.getProduct(), quantity));
			//product by name : sorted with importdate DESC and expDate asc
			List<Product> products = productService.getProductByName(productName);
			for(Product product:products) {
				if(product.getQuantity() > quantity) {
					productService.updateProductQuantity(product.getId(), product.getQuantity()-quantity);
					quantity = 0 ;
				}else {
					quantity -= product.getQuantity();
					productService.deleteProduct(productName, product.getExpireDate(), product.getImportDate());
				}
				if(quantity == 0) {
					break;
				}
			}
		}
		cartItemSevice.deleteAll();
		cart.setCartItem(null);
		cartRepository.save(cart);
		order.setTotal(total);
		order.setOrderLine(orderLine);
		return orderRepository.save(order);
	}
	public void deleteOrder(Long id) {
		if(!orderRepository.existsById(id)) {
			throw new IllegalStateException("Order does not exist");
		}
		orderRepository.deleteById(id);
	}
	public void deleteAll() {
		orderRepository.deleteAll();
	}
	@Transactional
	public void updateOrder(Order order, Long id) {
		if(!orderRepository.existsById(id)) {
			throw new IllegalStateException("Order does not exist");
		}
		Order currentOrder = orderRepository.findById(id).get();
		currentOrder.setDeliveryNotes(order.getDeliveryNotes());
		currentOrder.setDeliveryTime(order.getDeliveryTime());
		currentOrder.setCreatedTime(order.getCreatedTime());
		currentOrder.setStatus(order.getStatus());
		currentOrder.setOrderLine(order.getOrderLine());
		currentOrder.setTotal(order.getTotal());
		orderRepository.save(currentOrder);
	}
	
}
