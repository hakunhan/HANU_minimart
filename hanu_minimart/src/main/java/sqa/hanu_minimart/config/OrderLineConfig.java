package sqa.hanu_minimart.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sqa.hanu_minimart.model.Order;
import sqa.hanu_minimart.repository.OrderLineRepository;
import sqa.hanu_minimart.model.OrderLine;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OrderLineConfig {
    @Bean
    CommandLineRunner orderLineCommandLineRunner (OrderLineRepository orderLineRepository){
        return args -> {
            OrderLine o1 = new OrderLine(1,1,2,"bread",5);
            OrderLine o2 = new OrderLine(2,1,4,"milk",6);
            List<OrderLine> list =  new ArrayList<>();
            list.add(o1);
            list.add(o2);
            orderLineRepository.saveAll(list);
        };
    }
}
