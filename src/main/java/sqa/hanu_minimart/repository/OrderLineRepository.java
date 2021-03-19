package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqa.hanu_minimart.model.OrderLine;


import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    Optional<OrderLine> findById(Long orderID);

    List<OrderLine> findByOrder_Id(Long orderId);

}
