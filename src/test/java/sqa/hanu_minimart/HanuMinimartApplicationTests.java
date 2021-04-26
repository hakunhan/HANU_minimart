package sqa.hanu_minimart;

//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.repository.ProductRepository;
import sqa.hanu_minimart.service.ProductService;

import java.time.LocalDate;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
class HanuMinimartApplicationTests{

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

//    @Before
//    public void setUp(){
//        Mockito.when(productRepository.findAll())
//                .thenReturn((List<Product>) new Product());
//    }
}
