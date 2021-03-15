package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sqa.hanu_minimart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
}
