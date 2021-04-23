package sqa.hanu_minimart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sqa.hanu_minimart.model.OrderLine;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.model.ProductStatus;
import sqa.hanu_minimart.payload.ProductPayLoad;
import sqa.hanu_minimart.service.ProductService;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
public class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    public void testAddNewProduct() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddNewProduct2() throws Exception {
        ProductPayLoad product = new ProductPayLoad();
        product.setExpireDate(LocalDate.now().plusDays(30).toString());
        product.setPrice(10.0);
        product.setName("Name");
        product.setCategory("FOOD");
        product.setDescription("The characteristics of someone or something");
        product.setQuantity(2);
        product.setPicture_URL("https://example.org/example");
        product.setStatus(ProductStatus.NEW);
        String content = (new ObjectMapper()).writeValueAsString(product);
        System.err.println(content);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/delete/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetCategorys() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getCategory");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetHomepageProducts() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/homepage/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetProductNearExpireDate() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/nearExpire");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetProducts() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAll");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetProductsQuantity() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/checkStorage/{name}", "value");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

