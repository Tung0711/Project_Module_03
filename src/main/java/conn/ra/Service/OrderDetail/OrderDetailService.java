package conn.ra.Service.OrderDetail;

import conn.ra.Model.Entity.OrderDetails;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetails> getByOrderId(Long orderId);
}
