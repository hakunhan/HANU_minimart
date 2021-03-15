package sqa.hanu_minimart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.service.OrderService;
import sqa.hanu_minimart.model.Order;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/order"})
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping(path = {"/all"})
    public List<Order> getAllOrder() { return orderService.getOrder();}

    @PostMapping(path = {"/placeOrder"})
    public void placeOrder (@RequestBody Order order) { orderService.placeOrder(order);}

    @GetMapping(path = {"/pendingOrder"})
    public List<Order> getPendingOrder() { return orderService.getPendingOrder(); }

    @DeleteMapping(path = {"/delete{orderID}"})
    public void deleteOrder(@PathVariable("id") int orderID){
        orderService.deleteOrder(orderID);
    }

    @PutMapping(path = {"/process/{orderID}"})
    public void processOrder(@PathVariable("orderID") int orderID){
        orderService.processOrder(orderID);
    }
}
