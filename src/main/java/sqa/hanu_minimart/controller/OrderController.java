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

    @GetMapping(path = {"/getAll"})
    public List<Order> getAllOrder() { return orderService.getAllOrders();}

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/getByUserId")
    public List<Order> getByUserId(@RequestParam Long userId){
        return orderService.getOrderByUserId(userId);
    }

    @PostMapping(path = "/add")
    public Order newOrder(@RequestBody OrderPayload orderPayload) {
        return orderService.addNewOrder(orderPayload);
    }


    @DeleteMapping(path = {"/delete/{orderID}"})
    public void deleteOrder(@PathVariable Long orderID){
        orderService.deleteOrder(orderID);
    }

//    @PutMapping(path = {"/process/{orderID}"})
//    public void processOrder(@PathVariable("orderID")Long orderID){
//        orderService.processOrder(orderID);
//    }
    @PutMapping("/update/{id}")
    public void updateOrder(@RequestBody Order order, @PathVariable Long id) {
        orderService.updateOrder(order, id);
    }

    // order/updateStatus/1?status=Accepted (hoac Cancel)
    @PutMapping(path = "/updateStatus/{id}")
    public void updateOrderStatus(@PathVariable Long id,
                                  @RequestParam String status){
        orderService.updateOrderStatus(id, status);
    }
}
