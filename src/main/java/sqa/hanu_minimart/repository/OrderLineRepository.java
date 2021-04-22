package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sqa.hanu_minimart.model.OrderLine;


import java.util.List;
import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    Optional<OrderLine> findById(Long orderID);
}
