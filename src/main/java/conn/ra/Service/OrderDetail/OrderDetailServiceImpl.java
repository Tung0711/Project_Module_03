package conn.ra.Service.OrderDetail;

import conn.ra.Model.Entity.OrderDetails;
import conn.ra.Model.Entity.Orders;
import conn.ra.Model.Entity.Product;
import conn.ra.Repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetails> getByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
    @Override
    public OrderDetails add(Product product, Orders orders, int orderQuantity) {
        OrderDetails orderDetail = OrderDetails.builder()
                .orders(orders)
                .product(product)
                .name(product.getProductName ())
                .price(product.getPrice())
                .orderQuantity (orderQuantity)
                .build();
        return orderDetailRepository.save(orderDetail);
    }
}
