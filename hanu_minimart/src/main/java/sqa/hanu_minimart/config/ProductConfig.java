package sqa.hanu_minimart.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sqa.hanu_minimart.model.Product;
import sqa.hanu_minimart.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            Product bread = new Product("Bread", 2000, 2, "FOOD", LocalDate.now());
            Product candy = new Product("Candy", 10000, 10, "FOOD", LocalDate.now());
            candy.setPicture_URL("http://qnimate.com/wp-content/uploads/2014/03/images2.jpg");
            List item = new ArrayList();
            item.add(bread);
            item.add(candy);
            productRepository.saveAll(item);

        };
    }
}
