package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sqa.hanu_minimart.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * from orders ORDER BY status DESC", nativeQuery = true)
    List<Order> findAllOrder();

    List<Order> findByUser_Id(Long userId);
}
