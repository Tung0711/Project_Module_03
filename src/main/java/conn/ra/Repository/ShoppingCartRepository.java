package conn.ra.Repository;

import conn.ra.Model.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    @Query("SELECT s from ShoppingCart s where s.user.id = :id")
    List<ShoppingCart> findByUsers(@Param("id") Long id);

    @Query("SELECT s from ShoppingCart s where s.product.id = :productId and s.user.id = :userId")
    ShoppingCart findByUserAndProduct(@Param("userId") Long userId, @Param("productId") Long productId);
}
