package sqa.hanu_minimart.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sqa.hanu_minimart.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll (Pageable pageable);

    Page<User> findById (Long id, Pageable pageable);

    Page<User> findByPhoneNumber(String phoneNumber, Pageable pageable);

    Optional<User> findByUsernameOrPhoneNumber(String username, String phoneNumber);

    Page<User> findByUsername(String username, Pageable pageable);

    Boolean existsByUsername(String username);

    Boolean existsByPhoneNumber(String phoneNumber);

    Page<User> findByNameContainingOrAddressContaining(String name, String address, Pageable pageable);
    
    Optional<User> findById(Long id);
}