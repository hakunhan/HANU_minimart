package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.service.OrderLineService;
import sqa.hanu_minimart.model.OrderLine;

import java.util.List;

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
    public void createNewOrderLine(@RequestParam(required = false) int orderID,
                                   @RequestParam(required = false) String product,
                                   @RequestParam(required = false) int quantity,
                                   @RequestParam(required = false) int orderLineID){
        orderLineService.createNewOrderLine(orderID, orderLineID, quantity, product);
    }

    @DeleteMapping(path ={ "/delete/{id}"})
    public void deleteOrderLine(@PathVariable("id") int orderLineID){
        orderLineService.deleteOrderLine(orderLineID);
    }

    @GetMapping(path = {"/bill"})
    public double getTotalBill(int orderID){
         return orderLineService.getTotalBill(orderID);
    }
}
