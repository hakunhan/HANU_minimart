package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.service.ProductService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/product"})
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all")
    public List<Product> getAllProduct(){
        return productService.getProduct();
    }

    @GetMapping(path = "/new")
    public List<Product> getNewProduct(){
        return productService.getNewProduct();
    }

    @PostMapping(path = "/getByName")
    public List<Product> getProductByName(@RequestBody String name){
        return productService.getProductByName(name);
    }

    @GetMapping(path = "/nearExpire")
    public List<Product> getProductNearExpireDate(){
        return productService.getProductNearExpireDate();
    }

    @PostMapping(path = "/add")
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @PutMapping(path = "{id}")
    public void updateProduct(@PathVariable("id") int id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) int quantity,
                              @RequestParam(required = false) double price,
                              @RequestParam(required = false) String category,
                              @RequestParam(required = false) LocalDate expireDate,
                              @RequestParam(required = false) String status
                              ){
        productService.updateProductQuantity(id, name, quantity, price, category, expireDate, status);
    }

    @DeleteMapping(path = {"{id}"})
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }
}
