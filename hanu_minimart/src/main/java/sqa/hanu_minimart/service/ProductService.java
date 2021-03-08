package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.repository.ProductRepository;

import javax.naming.directory.InvalidAttributesException;
import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public void deleteProduct(int id) {
        boolean exists = productRepository.existsById(id);
        if (!exists){
            throw new IllegalStateException("Product does not exist!");
        }
        productRepository.deleteById(id);
    }

    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> getProductNearExpireDate() {
        return productRepository.findNearlyExpireProduct();
    }

    public List<Product> getNewProduct() {
        return productRepository.findNewestImportProduct();
    }

    @Transactional
    public void updateProductQuantity(int id, String name, int quantity, double price, String category, LocalDate expireDate, String status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        if(name != null && name.length() > 0 && !Objects.equals(name, product.getName())){
            product.setName(name);
        }

        if(quantity != 0 && !Objects.equals(quantity, product.getQuantity())){
            product.setQuantity(quantity);
        }

        if(price != 0 && !Objects.equals(price, product.getPrice())){
            product.setPrice(price);
        }

        if(category != null && category.length() > 0 && !Objects.equals(category, product.getCategory())){
            product.setCategory(category);
        }

        if(expireDate != null && !Objects.equals(expireDate, product.getExpireDate())){
            product.setExpireDate(expireDate);
        }
        product.setStatus(status);
     
    }
}
