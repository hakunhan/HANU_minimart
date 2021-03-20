package sqa.hanu_minimart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sqa.hanu_minimart.model.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{
	@Modifying
    @Transactional 
	@Query(value = "Update order_line set order_fk = :orderId where id = :orderItemId", nativeQuery = true)
	void update(@Param("orderId") Long orderId,@Param("orderItemId") Long orderItemId);
}
