package conn.ra.Repository;

import conn.ra.Model.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("select o from Orders o where o.user.id = :userId")
    List<Orders> findAllByUserId(Long userId);
    @Query("select o from Orders o where o.user.id = :userId and o.serialNumber = :serialNumber")
    Orders findByUserIdAndSerial(Long userId, String serialNumber);
}
