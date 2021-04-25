package sqa.hanu_minimart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.ProductStatus;
import sqa.hanu_minimart.repository.CartItemRepository;
import sqa.hanu_minimart.repository.ProductRepository;

@ContextConfiguration(classes = {OrderLineService.class, ProductService.class})
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private OrderLineService orderLineService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testGetHomepageProducts() {
        when(this.productRepository.findProductsById((Long) any())).thenReturn(new ArrayList<Product>());
        assertTrue(
                this.productService.getHomepageProducts(123L, "Name", 10.0, 1, "Category", "Status", "2020-03-01", "2020-03-01")
                        .isEmpty());
        verify(this.productRepository).findProductsById((Long) any());
    }

    @Test
    public void testGetHomepageProducts2() {
        Product product = new Product();
        product.setExpireDate(LocalDate.ofEpochDay(1L));
        product.setPrice(10.0);
        product.setName("Name");
        product.setCategory("Category");
        product.setProductStatus(ProductStatus.HOT);
        product.setDescription("The characteristics of someone or something");
        product.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setSale(0);
        product.setId(123L);
        product.setOrderLine(new HashSet<OrderLine>());
        product.setQuantity(0);
        product.setPicture_URL("https://example.org/example");
        product.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setStatus(ProductStatus.HOT);

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(product);
        when(this.productRepository.findProductsById((Long) any())).thenReturn(productList);
        assertEquals(1,
                this.productService.getHomepageProducts(123L, "Name", 10.0, 1, "Category", "Status", "2020-03-01", "2020-03-01")
                        .size());
        verify(this.productRepository).findProductsById((Long) any());
    }

    @Test
    public void testGetCategory() {
        ArrayList<String> stringList = new ArrayList<String>();
        when(this.productRepository.findDistinctCategory()).thenReturn(stringList);
        List<String> actualCategory = this.productService.getCategory();
        assertSame(stringList, actualCategory);
        assertTrue(actualCategory.isEmpty());
        verify(this.productRepository).findDistinctCategory();
    }

    @Test
    public void testGetProducts() {
        ArrayList<Product> productList = new ArrayList<Product>();
        when(this.productRepository.findProductsById((Long) any())).thenReturn(productList);
        List<Product> actualProducts = this.productService.getProducts(123L, "Name", 10.0, 1, "Category", "Status",
                "2020-03-01", "2020-03-01");
        assertSame(productList, actualProducts);
        assertTrue(actualProducts.isEmpty());
        verify(this.productRepository).findProductsById((Long) any());
    }

    @Test
    public void testGetProducts2() {
        ArrayList<Product> productList = new ArrayList<Product>();
        when(this.productRepository.findByNameContaining(anyString())).thenReturn(productList);
        when(this.productRepository.findProductsById((Long) any())).thenReturn(new ArrayList<Product>());
        List<Product> actualProducts = this.productService.getProducts(0L, "Name", 10.0, 1, "Category", "Status",
                "2020-03-01", "2020-03-01");
        assertSame(productList, actualProducts);
        assertTrue(actualProducts.isEmpty());
        verify(this.productRepository).findByNameContaining(anyString());
    }

    @Test
    public void testGetProductByName() {
        ArrayList<Product> productList = new ArrayList<Product>();
        when(this.productRepository.findByNameContaining(anyString())).thenReturn(productList);
        List<Product> actualProductByName = this.productService.getProductByName("Name");
        assertSame(productList, actualProductByName);
        assertTrue(actualProductByName.isEmpty());
        verify(this.productRepository).findByNameContaining(anyString());
    }

    @Test
    public void testGetProductsQuantity() {
        when(this.productRepository.getProductQuantity(anyString())).thenReturn(1);
        assertEquals(1, this.productService.getProductsQuantity("Name").intValue());
        verify(this.productRepository).getProductQuantity(anyString());
    }

    @Test
    public void testAddNewProduct() {
        Product product = new Product();
        product.setExpireDate(LocalDate.ofEpochDay(1L));
        product.setPrice(10.0);
        product.setName("Name");
        product.setCategory("Category");
        product.setProductStatus(ProductStatus.HOT);
        product.setDescription("The characteristics of someone or something");
        product.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setSale(1);
        product.setId(123L);
        product.setOrderLine(new HashSet<OrderLine>());
        product.setQuantity(1);
        product.setPicture_URL("https://example.org/example");
        product.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setStatus(ProductStatus.HOT);
        when(this.productRepository.save((Product) any())).thenReturn(product);
        this.productService.addNewProduct(new Product());
        verify(this.productRepository).save((Product) any());
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(this.productRepository).deleteById((Long) any());
        when(this.productRepository.existsById((Long) any())).thenReturn(true);
        this.productService.deleteProduct(123L);
        verify(this.productRepository).deleteById((Long) any());
        verify(this.productRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteProduct2() {
        doNothing().when(this.productRepository).deleteById((Long) any());
        when(this.productRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.productService.deleteProduct(123L));
        verify(this.productRepository).existsById((Long) any());
    }

    @Test
    public void testGetProductNearExpireDate() {
        ArrayList<Product> productList = new ArrayList<Product>();
        when(this.productRepository.findNearlyExpireProduct()).thenReturn(productList);
        List<Product> actualProductNearExpireDate = this.productService.getProductNearExpireDate();
        assertSame(productList, actualProductNearExpireDate);
        assertTrue(actualProductNearExpireDate.isEmpty());
        verify(this.productRepository).findNearlyExpireProduct();
    }

    @Test
    public void testFindProductByNameSortedByExpAndImportDate() {
        ArrayList<Product> productList = new ArrayList<Product>();
        when(this.productRepository.findProductByNameSortedByExpAndImportDate(anyString())).thenReturn(productList);
        List<Product> actualFindProductByNameSortedByExpAndImportDateResult = this.productService
                .findProductByNameSortedByExpAndImportDate("Name");
        assertSame(productList, actualFindProductByNameSortedByExpAndImportDateResult);
        assertTrue(actualFindProductByNameSortedByExpAndImportDateResult.isEmpty());
        verify(this.productRepository).findProductByNameSortedByExpAndImportDate(anyString());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setExpireDate(LocalDate.ofEpochDay(1L));
        product.setPrice(10.0);
        product.setName("Name");
        product.setCategory("Category");
        product.setProductStatus(ProductStatus.HOT);
        product.setDescription("The characteristics of someone or something");
        product.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setSale(1);
        product.setId(123L);
        product.setOrderLine(new HashSet<OrderLine>());
        product.setQuantity(1);
        product.setPicture_URL("https://example.org/example");
        product.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setStatus(ProductStatus.HOT);
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        this.productService.updateProduct(123L, "Name", 10.0, 1, "Category", "The characteristics of someone or something",
                "https://example.org/example", "20", "Status", "2020-03-01");
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    public void testUpdateProduct2() {
        Product product = new Product();
        product.setExpireDate(LocalDate.ofEpochDay(1L));
        product.setPrice(0.0);
        product.setName("null");
        product.setCategory("null");
        product.setProductStatus(ProductStatus.HOT);
        product.setDescription("null");
        product.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setSale(0);
        product.setId(123L);
        product.setOrderLine(new HashSet<OrderLine>());
        product.setQuantity(1);
        product.setPicture_URL("null");
        product.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setStatus(ProductStatus.HOT);
        Optional<Product> ofResult = Optional.<Product>of(product);
        doNothing().when(this.productRepository).updatePictureURL(anyString(), anyString());
        doNothing().when(this.productRepository).updateDescription(anyString(), anyString());
        doNothing().when(this.productRepository).updateCategory(anyString(), anyString());
        doNothing().when(this.productRepository).updateName(anyString(), anyString());
        doNothing().when(this.productRepository).updatePrice(anyString(), anyDouble());
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        this.productService.updateProduct(123L, "Name", 10.0, 1, "Category", "The characteristics of someone or something",
                "https://example.org/example", "null", "Status", "2020-03-01");
        verify(this.productRepository).updateDescription(anyString(), anyString());
        verify(this.productRepository).updateCategory(anyString(), anyString());
        verify(this.productRepository).updatePictureURL(anyString(), anyString());
        verify(this.productRepository).findById((Long) any());
        verify(this.productRepository).updatePrice(anyString(), anyDouble());
        verify(this.productRepository).updateName(anyString(), anyString());
    }

    @Test
    public void testUpdateProductQuantity() {
        Product product = new Product();
        product.setExpireDate(LocalDate.ofEpochDay(1L));
        product.setPrice(10.0);
        product.setName("Name");
        product.setCategory("Category");
        product.setProductStatus(ProductStatus.HOT);
        product.setDescription("The characteristics of someone or something");
        product.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setSale(1);
        product.setId(123L);
        product.setOrderLine(new HashSet<OrderLine>());
        product.setQuantity(1);
        product.setPicture_URL("https://example.org/example");
        product.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setStatus(ProductStatus.HOT);
        Optional<Product> ofResult = Optional.<Product>of(product);

        Product product1 = new Product();
        product1.setExpireDate(LocalDate.ofEpochDay(1L));
        product1.setPrice(10.0);
        product1.setName("Name");
        product1.setCategory("Category");
        product1.setProductStatus(ProductStatus.HOT);
        product1.setDescription("The characteristics of someone or something");
        product1.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setSale(1);
        product1.setId(123L);
        product1.setOrderLine(new HashSet<OrderLine>());
        product1.setQuantity(1);
        product1.setPicture_URL("https://example.org/example");
        product1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setStatus(ProductStatus.HOT);
        when(this.productRepository.save((Product) any())).thenReturn(product1);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        this.productService.updateProductQuantity(123L, 1);
        verify(this.productRepository).save((Product) any());
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    public void testUpdateProductQuantity2() {
        Product product = new Product();
        product.setExpireDate(LocalDate.ofEpochDay(1L));
        product.setPrice(10.0);
        product.setName("Name");
        product.setCategory("Category");
        product.setProductStatus(ProductStatus.HOT);
        product.setDescription("The characteristics of someone or something");
        product.setImportDate(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setSale(1);
        product.setId(123L);
        product.setOrderLine(new HashSet<OrderLine>());
        product.setQuantity(1);
        product.setPicture_URL("https://example.org/example");
        product.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setStatus(ProductStatus.HOT);
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.<Product>empty());
        assertThrows(IllegalStateException.class, () -> this.productService.updateProductQuantity(123L, 1));
        verify(this.productRepository).findById((Long) any());
    }
}

