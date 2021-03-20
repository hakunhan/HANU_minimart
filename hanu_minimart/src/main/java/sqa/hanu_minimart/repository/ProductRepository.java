package sqa.hanu_minimart.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sqa.hanu_minimart.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Modifying
	@Transactional
	@Query(value = "Delete from product where name = ?1 and expire_date = ?2 and import_date = ?3", nativeQuery = true)
	void deleteByNameAndExp(String name, LocalDate exp, LocalDateTime importDate);
	
    List<Product> findByNameContaining(String name);

    List<Product> findByCategory(String category);

    List<Product> findByProductStatus(String status);

    @Query(value = "SELECT * FROM product GROUP BY name ORDER BY import_date DESC ", nativeQuery = true)
    List<Product> findNewestImportProduct();

    @Query(value = "SELECT * FROM product WHERE expire_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 90 DAY) ORDER BY import_date ASC", nativeQuery = true)
    List<Product> findNearlyExpireProduct();
    
    @Query(value = "Select * from product Where name = ?1 order by import_date ASC, expire_date DESc ", nativeQuery = true)
    List<Product> findProductByIdSortedByExpAndImportDate(String name);
}
