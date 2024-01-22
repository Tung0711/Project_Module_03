package conn.ra.Repository;

import conn.ra.Model.Entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
    @Query("select od from OrderDetails od where od.orders.id = :orderId")
    List<OrderDetails> findByOrderId(Long orderId);
}
