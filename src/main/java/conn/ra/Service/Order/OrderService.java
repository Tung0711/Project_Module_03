package conn.ra.Service.Order;

import conn.ra.Model.Entity.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> getAll(Long userId);
    Orders add(Long userId);

    Orders getBySerial(Long userId, String serial);
}
