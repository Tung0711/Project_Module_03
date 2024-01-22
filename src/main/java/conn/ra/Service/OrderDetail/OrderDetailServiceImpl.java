package conn.ra.Service.OrderDetail;

import conn.ra.Model.Entity.OrderDetails;
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
}
