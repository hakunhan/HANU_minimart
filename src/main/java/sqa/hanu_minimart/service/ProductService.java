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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getHomepageProducts(Integer id, String name, Double price, Integer quantity, String category, String status, String importDate, String expireDate) {
        List<Product> query;

        if (id > 0){
            query = productRepository.findProductsById(id);
        }
        else if (name.length() > 0){
            query = productRepository.findByNameContaining(name);
        }
        else if (price > 0){
            query = productRepository.findProductsByPrice(price);
        }
        else if (quantity > 0){
            query = productRepository.findProductsByQuantity(quantity);
        }
        else if (category.length() > 0){
            query = productRepository.findByCategory(category);
        }
        else if (status.length() > 0){
            query = productRepository.findByStatus(status);
        }
        else if (!importDate.equals("2000-03-21")){
            query = productRepository.findByImportDate(LocalDate.parse(importDate));
        }
        else if (!expireDate.equals("2000-03-21")){
            query = productRepository.findByExpireDate(LocalDate.parse(expireDate));
        }
        else {
            query = productRepository.findAll();
        }

        return query.stream()
                .filter(distinctByKey(Product::getName) )
                .collect( Collectors.toList() );
    }

    //support method for getting distinct object
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<String> getCategory() {
        return productRepository.findDistinctCategory();
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

    @Transactional
    public void updateProduct(int id, String name, double price, int quantity, String category, String description, String picture_URL, Integer sale, String status, String expireDate) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        if(name.length() > 0 && !Objects.equals(name, product.getName())){
            productRepository.updateName(product.getName(), name);
        }

        if(quantity > 0 && quantity != product.getQuantity()){
            product.setQuantity(quantity);
        }

        if(price > 0 && price != product.getPrice()){
            productRepository.updatePrice(product.getName(), price);
        }

        if(category.length() > 0 && !Objects.equals(category, product.getCategory())){
            productRepository.updateCategory(product.getName(), category);
        }

        if(description.length() > 0 &&!Objects.equals(description, product.getDescription())){
            productRepository.updateDescription(product.getName(), description);
        }

        if(picture_URL.length() > 0 &&!Objects.equals(picture_URL, product.getPicture_URL())){
            productRepository.updatePictureURL(product.getName(), picture_URL);
        }

        if(sale >= 0 && sale < 100){
            if (sale > 0 && (product.getSale() == null || product.getSale() == 0)){
                productRepository.updatePrice(product.getName(),product.getPrice() * (100 - sale) / 100);
            }else if(sale > 0 && product.getSale() > 0){
                productRepository.updatePrice(product.getName(), product.getPrice() / (100 - product.getSale()) * (100 -sale));
            } else if(sale == 0 && product.getSale() != null && product.getSale() > 0){
                productRepository.updatePrice(product.getName(),product.getPrice() / (100 - product.getSale()) * 100);
            }

            productRepository.updateSale(product.getName(), sale);
        }

        if(status.equalsIgnoreCase("hot")){
            productRepository.updateStatus(product.getName(), ProductStatus.HOT.toString());
        }else if (status.equalsIgnoreCase("new")){
            productRepository.updateStatus(product.getName(), ProductStatus.NEW.toString());
        }

        productRepository.save(product);
    }


    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }


}
