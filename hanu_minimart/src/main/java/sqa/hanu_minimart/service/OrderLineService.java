package sqa.hanu_minimart.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.repository.OrderLineRepository;

@Service
public class OrderLineService {
	@Autowired
	private OrderLineRepository orderLineRepository;
	private ProductService productService;
	public OrderLineService() {}
	public List<OrderLine> getAllOrderItem(){
		return orderLineRepository.findAll();
	}
	public OrderLine getByid(Long id) {
		return orderLineRepository.findById(id).get();
	}
	public void addnewOrderItem(OrderLine orderItem) {
		orderLineRepository.save(orderItem);
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
