package sqa.hanu_minimart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.ProductStatus;
import sqa.hanu_minimart.repository.CartItemRepository;
import sqa.hanu_minimart.repository.ProductRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final CartItemRepository cartItemRepository;
    private final OrderLineService orderLineService;

    @Autowired
    public ProductService(ProductRepository productRepository, CartItemRepository cartItemRepository, OrderLineService orderLineService) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderLineService = orderLineService;
    }

    public List<Product> getHomepageProducts(Long id, String name, Double price, Integer quantity, String category, String status, String importDate, String expireDate) {
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
            query = productRepository.findByProductStatus(status);
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

    public List<Product> getProducts(Long id, String name, Double price, Integer quantity, String category, String status, String importDate, String expireDate) {
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
            return productRepository.findByProductStatus(status);
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

    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    public Integer getProductsQuantity(String name) {
        return productRepository.getProductQuantity(name);
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

    public List<Product> getProductNearExpireDate() {
        return productRepository.findNearlyExpireProduct();
    }

    public List<Product> findProductByNameSortedByExpAndImportDate(String name){
        return productRepository.findProductByNameSortedByExpAndImportDate(name);
    }

    @Transactional
    public void updateProduct(Long id, String name, Double price, Integer quantity, String category, String description, String picture_URL, String sale, String status, String expireDate) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        if(name.length() > 0 && !Objects.equals(name, product.getName())){
            productRepository.updateName(product.getName(), name);
        }

        if(quantity > 0 && quantity != product.getQuantity()){
            product.setQuantity(quantity);
        }

        if(price > 0 && !price.equals(product.getPrice())){
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

        if(!sale.equals("null") && Integer.parseInt(sale) >= 0 && Integer.parseInt(sale) < 100){
            if (Integer.parseInt(sale) > 0 && (product.getSale() == null || product.getSale() == 0)){
                productRepository.updatePrice(product.getName(),product.getPrice() * (100 - Integer.parseInt(sale)) / 100);
            }else if(Integer.parseInt(sale) > 0 && product.getSale() > 0){
                productRepository.updatePrice(product.getName(), product.getPrice() / (100 - product.getSale()) * (100 - Integer.parseInt(sale)));
            } else if(Integer.parseInt(sale) == 0 && product.getSale() != null && product.getSale() > 0){
                productRepository.updatePrice(product.getName(),product.getPrice() / (100 - product.getSale()) * 100);
            }

            productRepository.updateSale(product.getName(), Integer.parseInt(sale));
        }

        if(status.equalsIgnoreCase("hot")){
            productRepository.updateStatus(product.getName(), ProductStatus.HOT.toString());
        }else if (status.equalsIgnoreCase("new")){
            productRepository.updateStatus(product.getName(), ProductStatus.NEW.toString());
        }

        if(!LocalDate.parse(expireDate).equals(product.getExpireDate())){
            System.out.println(expireDate);
            product.setExpireDate(LocalDate.parse(expireDate));
        }

        productRepository.save(product);
    }

    @Transactional
    public void updateProductQuantity(Long id ,int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product does not exist!"));

        product.setQuantity(quantity);
        productRepository.save(product);
    }
}
