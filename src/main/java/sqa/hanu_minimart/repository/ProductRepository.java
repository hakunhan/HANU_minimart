package sqa.hanu_minimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sqa.hanu_minimart.model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByCategory(String category);

    List<Product> findByProductStatus(String status);

    @Query(value = "SELECT * FROM product GROUP BY name ORDER BY import_date DESC ", nativeQuery = true)
    List<Product> findNewestImportProduct();

    @Query(value = "SELECT * FROM product WHERE expire_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 90 DAY) ORDER BY import_date ASC", nativeQuery = true)
    List<Product> findNearlyExpireProduct();

    List<Product> findProductsById(Long id);

    List<Product> findProductsByPrice(Double price);

    List<Product> findProductsByQuantity(Integer quantity);

    @Query(value = "SELECT * FROM product where DATE(import_date) = ?", nativeQuery = true)
    List<Product> findByImportDate(LocalDate importDate);

    List<Product> findByExpireDate(LocalDate expireDate);

    @Query(value = "SELECT DISTINCT category FROM product", nativeQuery = true)
    List<String> findDistinctCategory();

    @Query(value = "Select * from product Where name = ?1 order by import_date ASC, expire_date DESc ", nativeQuery = true)
    List<Product> findProductByNameSortedByExpAndImportDate(String name);

    @Modifying
    @Query(value = "Update product SET category = ?2 WHERE name =?1", nativeQuery = true)
    void updateCategory(String name, String category);

    @Modifying
    @Query(value = "Update product SET price = ?2 WHERE name =?1", nativeQuery = true)
    void updatePrice(String name, double price);

    @Modifying
    @Query(value = "Update product SET description = ?2 WHERE name =?1", nativeQuery = true)
    void updateDescription(String name, String description);

    @Modifying
    @Query(value = "Update product SET picture_url = ?2 WHERE name =?1", nativeQuery = true)
    void updatePictureURL(String name, String picture_url);

    @Modifying
    @Query(value = "Update product SET sale = ?2 WHERE name =?1", nativeQuery = true)
    void updateSale(String name, Integer sale);

    @Modifying
    @Query(value = "Update product SET status = ?2 WHERE name =?1", nativeQuery = true)
    void updateStatus(String name, String status);

    @Modifying
    @Query(value = "Update product SET name = ?2 WHERE name =?1", nativeQuery = true)
    void updateName(String oldName, String newName);

    @Modifying
    @Transactional
    @Query(value = "Delete from product where name = ?1 and expire_date = ?2 and import_date = ?3", nativeQuery = true)
    void deleteByNameAndExp(String name, LocalDate exp, LocalDateTime importDate);
}
