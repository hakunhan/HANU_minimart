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

    List<OrderLine> findByOrder_Id(Long orderId);

    @Modifying
    @Transactional
    @Query(value = "Update order_line set order_fk = :orderId where id = :orderItemId", nativeQuery = true)
    void update(@Param("orderId") Long orderId, @Param("orderItemId") Long orderItemId);
}
