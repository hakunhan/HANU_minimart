package sqa.hanu_minimart.repository;

import sqa.hanu_minimart.model.Role;
import sqa.hanu_minimart.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

