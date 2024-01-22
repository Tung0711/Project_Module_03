package conn.ra.Repository;

import conn.ra.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductNameOrDescription(String productName, String description);
    boolean existsByProductName(String productName);
}
