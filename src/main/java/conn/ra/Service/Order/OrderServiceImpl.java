package conn.ra.Service.Order;

import conn.ra.Model.Entity.Orders;
import conn.ra.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Orders> getAll(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Orders add(Long userId) {

        return null;
    }

    @Override
    public Orders getBySerial(Long userId, String serial) {
        return orderRepository.findByUserIdAndSerial(userId, serial);
    }
}
