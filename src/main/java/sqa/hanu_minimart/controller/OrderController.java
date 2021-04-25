package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAllOrder() {
        try{
            return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<?> getByUserId(@RequestParam Long userId){
        try{
            return new ResponseEntity<>(orderService.getOrderByUserId(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> newOrder(@RequestBody OrderPayload orderPayload) {
        try{
            orderService.addNewOrder(orderPayload);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(path = {"/delete/{orderID}"})
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderID){
        try{
            orderService.deleteOrder(orderID);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@RequestBody Order order, @PathVariable Long id) {
        try{
            orderService.updateOrder(order, id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // order/updateStatus/1?status=Accepted (hoac Cancel)
    @PutMapping(path = "/updateStatus/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id,
                                  @RequestParam String status){
        try{
            orderService.updateOrderStatus(id, status);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
