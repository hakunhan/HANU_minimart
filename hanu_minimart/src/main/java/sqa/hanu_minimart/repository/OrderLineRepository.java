package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sqa.hanu_minimart.model.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer>{

}
