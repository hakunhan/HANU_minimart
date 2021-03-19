package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.service.OrderLineService;
import sqa.hanu_minimart.model.OrderLine;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"api/orderline"})
public class OrderLineController {
    private OrderLineService orderLineService;

    @Autowired
    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping(path = {"/all"})
    public List<OrderLine> getAllOrderLine(){ return orderLineService.getAllOrderLine();}

    @PostMapping(path = "/newOrderLine")
    public void createNewOrderLine(@RequestBody OrderLine orderItem){
        orderLineService.addNewOrderItem(orderItem);
    }

//    @DeleteMapping(path ={ "/delete/{id}"})
//    public void deleteOrderLine(@PathVariable("id") Long orderLineID){
//        orderLineService.deleteOrderLine(orderLineID);
//    }

//    @GetMapping(path = {"/bill"})
//    public double getTotalBill(Long orderID){
//         return orderLineService.getTotalBill(orderID);
//    }
}
