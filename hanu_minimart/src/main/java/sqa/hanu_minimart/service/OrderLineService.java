package sqa.hanu_minimart.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.repository.OrderLineRepository;

@Service
public class OrderLineService {
	@Autowired
	private OrderLineRepository orderLineRepository;
	@Autowired
	public OrderLineService() {}
	public List<OrderLine> getAllOrderItem(){
		return orderLineRepository.findAll();
	}
	public OrderLine getByid(Long id) {
		return orderLineRepository.findById(id).get();
	}
	public OrderLine addnewOrderItem(OrderLine orderItem) {
		return orderLineRepository.save(orderItem);
	}
	public void update(Long orderId, Long orderItemId) {
		orderLineRepository.update(orderId, orderItemId);
	}
	public void deleteAll() {
		orderLineRepository.deleteAll();
	}
	public void deleteById(Long id) {
		orderLineRepository.deleteById(id);
	}
	@Transactional
	public void update(OrderLine orderLine, Long id) {
		if(!orderLineRepository.existsById(id)) {
			throw new IllegalStateException("OrderItem does not exist.");
		}
		OrderLine currentOrderLine = orderLineRepository.findById(id).get();
		currentOrderLine.setQuantity(orderLine.getQuantity());
		orderLineRepository.save(currentOrderLine);
	}
}
