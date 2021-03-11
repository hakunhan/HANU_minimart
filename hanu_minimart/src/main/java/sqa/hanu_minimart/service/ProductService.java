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

    public List<Product> getProducts(Integer id, String name, Double price, Integer quantity, String category, String status, String importDate, String expireDate) {
        if(id > 0){
            return productRepository.findProductsById(id);
        }
        else if (name.length() > 0){
            return productRepository.findByNameContaining(name);
        }
        else if (price > 0){
            return productRepository.findProductsByPrice(price);
        }
        else if (quantity > 0){
            return productRepository.findProductsByQuantity(quantity);
        }
        else if (category.length() > 0){
            return productRepository.findByCategory(category);
        }
        else if (status.length() > 0){
            return productRepository.findByStatus(status);
        }
        else if (!importDate.equals("2000-03-21")){
            return productRepository.findByImportDate(LocalDate.parse(importDate));
        }
        else if (!expireDate.equals("2000-03-21")){
            return productRepository.findByExpireDate(LocalDate.parse(expireDate));
        }
        else {
            return productRepository.findAll();
        }
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

    public List<Product> getProductNearExpireDate() {
        return productRepository.findNearlyExpireProduct();
    }


    public void updateProduct(int id, String name, double price, int quantity, String category, String description, String picture_URL, Integer sale, String status, String expireDate) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        if(name.length() > 0 && !Objects.equals(name, product.getName())){
            product.setName(name);
        }

        if(quantity > 0 && quantity != product.getQuantity()){
            product.setQuantity(quantity);
        }

        if(price > 0 && price != product.getPrice()){
            product.setPrice(price);
        }

        if(category.length() > 0 && !Objects.equals(category, product.getCategory())){
            product.setCategory(category);
        }

        if(description.length() > 0 &&!Objects.equals(description, product.getDescription())){
            product.setDescription(description);
        }

        if(picture_URL.length() > 0 &&!Objects.equals(picture_URL, product.getPicture_URL())){
            product.setPicture_URL(picture_URL);
        }

        if(sale >= 0){
            if (sale > 0 && product.getSale() == 0){
                product.setPrice(product.getPrice() * sale / 100);
            }else if(sale == 0 && product.getSale() > 0){
                product.setPrice(product.getPrice() / product.getSale() * 100);
            }

            product.setSale(sale);
        }

        if(status.equalsIgnoreCase("hot")){
            product.setProductStatus(ProductStatus.HOT);
        }else if (status.equalsIgnoreCase("new")){
            product.setProductStatus(ProductStatus.NEW);
        }

        if(expireDate != null && expireDate.length() >0 && !expireDate.equals(product.getExpireDate().toString())){
            product.setExpireDate(LocalDate.parse(expireDate));
        }

        productRepository.save(product);
    }


    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }
}
