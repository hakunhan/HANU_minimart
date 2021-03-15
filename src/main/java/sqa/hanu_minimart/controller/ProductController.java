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

    // VD cách lấy: https//localhost:8085/api/product/homepage(...) -> thêm vào
    // muốn lấy hết product trong db -> /getAll
    // muốn lấy product theo id -> /getAll?id=
    // tương tự với name, price, ...
    @GetMapping(path = "/homepage/getAll")
    public List<Product> getHomepageProducts(@RequestParam (required = false, defaultValue = "-1") Integer id,
                                     @RequestParam (required = false, defaultValue = "") String name,
                                     @RequestParam (required = false, defaultValue = "-1") Double price,
                                     @RequestParam (required = false, defaultValue = "-1") Integer quantity,
                                     @RequestParam (required = false, defaultValue = "") String category,
                                     @RequestParam (required = false, defaultValue = "") String status,
                                     @RequestParam (required = false, defaultValue = "2000-03-21") String importDate,
                                     @RequestParam (required = false, defaultValue = "2000-03-21") String expireDate
                                     ){
        return productService.getHomepageProducts(id, name, price, quantity, category, status, importDate, expireDate);
    }

    // VD cách lấy: https//localhost:8085/api/product/(...) -> thêm vào
    // muốn lấy hết product trong db -> /getAll
    // muốn lấy product theo id -> /getAll?id=
    // tương tự với name, price, ...
    @GetMapping(path = "/getAll")
    public List<Product> getProducts(@RequestParam (required = false, defaultValue = "-1") Integer id,
                                     @RequestParam (required = false, defaultValue = "") String name,
                                     @RequestParam (required = false, defaultValue = "-1") Double price,
                                     @RequestParam (required = false, defaultValue = "-1") Integer quantity,
                                     @RequestParam (required = false, defaultValue = "") String category,
                                     @RequestParam (required = false, defaultValue = "") String status,
                                     @RequestParam (required = false, defaultValue = "2000-03-21") String importDate,
                                     @RequestParam (required = false, defaultValue = "2000-03-21") String expireDate
                                     ){
        return productService.getProducts(id, name, price, quantity, category, status, importDate, expireDate);
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
                              @RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "-1") Double price,
                              @RequestParam(required = false, defaultValue = "-1") Integer quantity,
                              @RequestParam(required = false, defaultValue = "") String category,
                              @RequestParam(required = false, defaultValue = "") String picture_URL,
                              @RequestParam(required = false, defaultValue = "") String description,
                              @RequestParam(required = false, defaultValue = "-1") Integer sale,
                              @RequestParam(required = false, defaultValue = "") String status,
                              @RequestParam(required = false, defaultValue = "") String expireDate
                              ){
        productService.updateProduct(id, name, price, quantity, category, description, picture_URL, sale, status, expireDate);
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public void deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
    }
}
