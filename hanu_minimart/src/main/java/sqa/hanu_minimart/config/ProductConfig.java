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
<<<<<<< HEAD

=======
            
>>>>>>> 6a5b5810694950d60ff1c9a01ade486bdaebe1fe
            List item = new ArrayList();
            item.add(bread);
            item.add(candy);
            productRepository.saveAll(item);
<<<<<<< HEAD

=======
>>>>>>> 6a5b5810694950d60ff1c9a01ade486bdaebe1fe
        };
    }
}
