package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sqa.hanu_minimart.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String name);

    @Query(value = "SELECT * FROM product GROUP BY name ORDER BY import_date DESC ", nativeQuery = true)
    List<Product> findNewestImportProduct();

    @Query(value = "SELECT * FROM product WHERE expire_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 90 DAY) ORDER BY import_date ASC", nativeQuery = true)
    List<Product> findNearlyExpireProduct();
}
