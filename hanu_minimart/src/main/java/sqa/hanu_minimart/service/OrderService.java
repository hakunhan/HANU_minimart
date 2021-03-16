package sqa.hanu_minimart.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.model.CartItem;
import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.User;
import sqa.hanu_minimart.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public OrderService() {}
	
	public List<Order> getAllOrder(){
		return orderRepository.findAll();
	}
	public Order getOrderById(int id) {
		return orderRepository.findById(id).get();
	}
	public Order addNewOrder(Order order) {
		return orderRepository.save(order);
	}
	public void deleteOrder(int id) {
		if(!orderRepository.existsById(id)) {
			throw new IllegalStateException("Order does not exist");
		}
		orderRepository.deleteById(id);
	}
	public void deleteAll() {
		orderRepository.deleteAll();
	}
	@Transactional
	public void updateOrder(Order order, int id) {
		if(!orderRepository.existsById(id)) {
			throw new IllegalStateException("Order does not exist");
		}
		Order oldOrder = orderRepository.findById(id).get();
		oldOrder.setDeliveryNotes(order.getDeliveryNotes());
		oldOrder.setDeliveryTime(order.getDeliveryTime());
		oldOrder.setCreatedTime(order.getCreatedTime());
		oldOrder.setStatus(order.getStatus());
		oldOrder.setOrderLine(order.getOrderLine());
		oldOrder.setTotal(order.getTotal());
		System.out.print("Updated");
	}
	
}
