package conn.ra.Service.OrderDetail;

import conn.ra.Model.Entity.OrderDetails;
import conn.ra.Model.Entity.Orders;
import conn.ra.Model.Entity.Product;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetails> getByOrderId(Long orderId);
    OrderDetails add(Product product, Orders orders, int orderQuantity);
}
