package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqa.hanu_minimart.model.OrderLine;


import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    List<OrderLine> findByOrderID(int orderID);

}
