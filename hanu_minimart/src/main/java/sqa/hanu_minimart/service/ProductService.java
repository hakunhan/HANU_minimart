package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.ProductStatus;
import sqa.hanu_minimart.repository.ProductRepository;

import javax.naming.directory.InvalidAttributesException;
import javax.transaction.Transactional;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Product does not exist!");
        }
        productRepository.deleteById(id);
    }
    public void deleteProduct(String name, LocalDate expireDate, LocalDateTime importDate) {
        productRepository.deleteByNameAndExp(name, expireDate, importDate);
    }
    public List<Product> getProductByName(String name) {
        System.out.println(name);
        return productRepository.findByNameContaining(name);
    }

    public List<Product> getProductNearExpireDate() {
        return productRepository.findNearlyExpireProduct();
    }
    public List<Product> findProductByIdSortedByExpAndImportDate(String name){
    	return productRepository.findProductByIdSortedByExpAndImportDate(name);
    }
    public List<Product> getNewProduct() {
        return productRepository.findNewestImportProduct();
    }
    @Transactional
    public void updateProductQuantity(Long id ,int quantity) {
    	Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));
    	if(quantity != 0 && quantity != product.getQuantity()){
            product.setQuantity(quantity);
        }
    	productRepository.save(product);
    }
    

    @Transactional
    public void updateProduct(Long id, String name, double price, int quantity, String category, String status,LocalDate expireDate) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        if(name != null && name.length() > 0 && !Objects.equals(name, product.getName())){
            product.setName(name);
        }

        if(quantity != 0 && quantity != product.getQuantity()){
            product.setQuantity(quantity);
        }

        if(price != 0 && price != product.getPrice()){
            product.setPrice(price);
        }

        if(category != null && category.length() > 0 && !Objects.equals(category, product.getCategory())){
            product.setCategory(category);
        }

        if(status.equalsIgnoreCase("hot")){
            product.setProductStatus(ProductStatus.HOT);
        }else if (status.equalsIgnoreCase("new")){
            product.setProductStatus(ProductStatus.NEW);
        }

        product.setStatus(status);

        if(expireDate != null  && !expireDate.equals(product.getExpireDate().toString())){
            product.setExpireDate(expireDate);
        }

        productRepository.save(product);
    }

    public List<Product> getProductByStatus(String status) {
        return productRepository.findByProductStatus(status);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);

    }
}
