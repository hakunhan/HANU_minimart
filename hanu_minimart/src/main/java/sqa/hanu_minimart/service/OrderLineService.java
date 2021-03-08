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
	public OrderLineService() {}
	public List<OrderLine> getAllOrderItem(){
		return orderLineRepository.findAll();
	}
	public OrderLine getByid(int id) {
		return orderLineRepository.findById(id).get();
	}
	public void addnewOrderItem(OrderLine orderItem) {
		orderLineRepository.save(orderItem);
	}
	public void deleteAll() {
		orderLineRepository.deleteAll();
	}
	public void deleteById(int id) {
		orderLineRepository.deleteById(id);
	}
	@Transactional
	public void update(OrderLine orderLine, int id) {
		if(!orderLineRepository.existsById(id)) {
			throw new IllegalStateException("OrderItem does not exist.");
		}
		OrderLine oldOrderLine = orderLineRepository.findById(id).get();
		oldOrderLine.setQuantity(orderLine.getQuantity());
	}
}
