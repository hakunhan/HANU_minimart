package sqa.hanu_minimart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.model.Cart;
import sqa.hanu_minimart.payload.OrderPayload;
import sqa.hanu_minimart.service.OrderService;
import sqa.hanu_minimart.model.Order;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/order"})
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping(path = {"/all"})
    public List<Order> getAllOrder() { return orderService.getAllOrders();}

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping(path = "/{id}")
    public Order newOrder(@RequestBody Cart cart, @PathVariable Long id) {
        return orderService.addNewOrder(cart, id);
    }

    @GetMapping(path = {"/pendingOrder"})
    public List<Order> getPendingOrder() { return orderService.getPendingOrder(); }

    @DeleteMapping(path = {"/delete{orderID}"})
    public void deleteOrder(@PathVariable("id") Long orderID){
        orderService.deleteOrder(orderID);
    }

//    @PutMapping(path = {"/process/{orderID}"})
//    public void processOrder(@PathVariable("orderID")Long orderID){
//        orderService.processOrder(orderID);
//    }
    @PutMapping("/{id}")
    public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
        orderService.updateOrder(order, id);
    }
}
