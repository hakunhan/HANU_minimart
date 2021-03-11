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
    public void updateProduct(@PathVariable("id") Integer id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false, defaultValue = "0") Double price,
                              @RequestParam(required = false, defaultValue = "0") Integer quantity,
                              @RequestParam(required = false, defaultValue = "") String category,
                              @RequestParam(required = false, defaultValue = "") String status,
                              @RequestParam(required = false, defaultValue = "") String expireDate
                              ){
        productService.updateProductQuantity(id, name, price, quantity, category,status, expireDate);
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }
}
