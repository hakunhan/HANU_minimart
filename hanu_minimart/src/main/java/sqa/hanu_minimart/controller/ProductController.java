package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.service.ProductService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/product"})
public class ProductController {
    private final ProductService productService;
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

    @GetMapping(path = "getByName/{name}")
    public List<Product> getProductByName(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }

    @GetMapping(path = "getByStatus/{status}")
    public List<Product> getProductByStatus(@PathVariable("status") String status){
        return productService.getProductByStatus(status);
    }

    @GetMapping(path = "getByCategory/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category){
        return productService.getProductByCategory(category);
    }

    @GetMapping(path = "/nearExpire")
    public List<Product> getProductNearExpireDate(){
        return productService.getProductNearExpireDate();
    }

    @PostMapping(path = "/add")
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }

    @PutMapping(path = "/update/{id}")
    public void updateProduct(@PathVariable("id") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) double price,
                              @RequestParam(required = false) int quantity,                             
                              @RequestParam(required = false) String category,
                              @RequestParam(required = false) String status,
                              @RequestParam(required = false) LocalDate expireDate
                              ){
       productService.updateProduct(id, name, price, quantity, category, status, expireDate);
    }
    @PutMapping(path = "/updateQuantity/{id}")
    public void updateProductQuantity(@PathVariable("id") Long id,
    								  @RequestParam(required = false) int quantity) {
    	productService.updateProductQuantity(id, quantity);
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}
