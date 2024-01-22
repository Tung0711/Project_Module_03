package conn.ra.Repository;

import conn.ra.Model.Entity.ERole;
import conn.ra.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}
