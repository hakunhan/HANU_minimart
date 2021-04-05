package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.service.OrderLineService;
import sqa.hanu_minimart.model.OrderLine;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"api/orderline"})
public class OrderLineController {
    private final OrderLineService orderLineService;

    @Autowired
    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping(path = {"/all"})
    public ResponseEntity<?> getAllOrderLine(){
        try{
            return new ResponseEntity<>(orderLineService.getAllOrderLine(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(orderLineService.getById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/newOrderLine")
    public ResponseEntity<?> createNewOrderLine(@RequestBody OrderLine orderItem){
        try{
            orderLineService.addNewOrderLine(orderItem);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrderLine orderItem, @PathVariable Long id) {
        try{
            orderLineService.update(orderItem, id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try{
            orderLineService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        try{
            orderLineService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
