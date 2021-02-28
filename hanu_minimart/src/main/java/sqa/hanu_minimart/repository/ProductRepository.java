package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sqa.hanu_minimart.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
