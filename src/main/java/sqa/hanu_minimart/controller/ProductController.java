package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.service.ProductService;


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
    public ResponseEntity<?> getHomepageProducts(@RequestParam (required = false, defaultValue = "-1") Integer id,
                                     @RequestParam (required = false, defaultValue = "") String name,
                                     @RequestParam (required = false, defaultValue = "-1") Double price,
                                     @RequestParam (required = false, defaultValue = "-1") Integer quantity,
                                     @RequestParam (required = false, defaultValue = "") String category,
                                     @RequestParam (required = false, defaultValue = "") String status,
                                     @RequestParam (required = false, defaultValue = "2000-03-21") String importDate,
                                     @RequestParam (required = false, defaultValue = "2000-03-21") String expireDate
                                     ){
        try{
            return new ResponseEntity<>(productService.getHomepageProducts(id, name, price, quantity, category, status, importDate, expireDate), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // VD cách lấy: https//localhost:8085/api/product/(...) -> thêm vào
    // muốn lấy hết product trong db -> /getAll
    // muốn lấy product theo id -> /getAll?id=
    // tương tự với name, price, ...
    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getProducts(@RequestParam (required = false, defaultValue = "-1") Integer id,
                                      @RequestParam (required = false, defaultValue = "") String name,
                                      @RequestParam (required = false, defaultValue = "-1") Double price,
                                      @RequestParam (required = false, defaultValue = "-1") Integer quantity,
                                      @RequestParam (required = false, defaultValue = "") String category,
                                      @RequestParam (required = false, defaultValue = "") String status,
                                      @RequestParam (required = false, defaultValue = "2000-03-21") String importDate,
                                      @RequestParam (required = false, defaultValue = "2000-03-21") String expireDate
                                     ){
        try{
            return new ResponseEntity<>(productService.getProducts(id, name, price, quantity, category, status, importDate, expireDate), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getCategory")
    public ResponseEntity<?> getCategorys(){
        try{
            return new ResponseEntity<>(productService.getCategory(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/nearExpire")
    public ResponseEntity<?> getProductNearExpireDate(){
        try{
            return new ResponseEntity<>(productService.getProductNearExpireDate(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addNewProduct(@RequestBody Product product){
        try{
            productService.addNewProduct(product);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Integer id,
                              @RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "-1") Double price,
                              @RequestParam(required = false, defaultValue = "-1") Integer quantity,
                              @RequestParam(required = false, defaultValue = "") String category,
                              @RequestParam(required = false, defaultValue = "") String picture_URL,
                              @RequestParam(required = false, defaultValue = "") String description,
                              @RequestParam(required = false, defaultValue = "-1") String sale,
                              @RequestParam(required = false, defaultValue = "") String status,
                              @RequestParam(required = false, defaultValue = "") String expireDate
                              ){
        try{
            productService.updateProduct(id, name, price, quantity, category, description, picture_URL, sale, status, expireDate);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
