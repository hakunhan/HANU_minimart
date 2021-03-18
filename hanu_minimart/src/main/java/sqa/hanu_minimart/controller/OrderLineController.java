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
import org.springframework.web.bind.annotation.RestController;

import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.service.OrderLineService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/orderLines"})
public class OrderLineController {
	@Autowired
	private OrderLineService orderItemService;
	@GetMapping
	public List<OrderLine> getAll(){
		return orderItemService.getAllOrderItem();
	}
	@GetMapping("/{id}")
	public OrderLine getById(@PathVariable Long id) {
		return orderItemService.getByid(id);
	}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		orderItemService.deleteById(id);
	}
	@DeleteMapping
	public void deleteAll() {
		orderItemService.deleteAll();
	}
	@PostMapping
	public void addNew(@RequestBody OrderLine orderItem) {
		orderItemService.addnewOrderItem(orderItem);
	}
	@PutMapping("/{id}")
	public void update(@RequestBody OrderLine orderItem, @PathVariable Long id) {
		orderItemService.update(orderItem, id);
	}
}
