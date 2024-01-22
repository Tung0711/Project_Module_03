package conn.ra.Controller.User;

import conn.ra.Model.Entity.OrderDetails;
import conn.ra.Model.Entity.Orders;
import conn.ra.Service.Order.OrderService;
import conn.ra.Service.OrderDetail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static conn.ra.Controller.User.AccountController.getUserId;

@RestController
@RequestMapping("/v1/user/history")
public class HistoryController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("")
    public ResponseEntity<List<Orders>> getAll() {
        Long userId = getUserId();
        List<Orders> orders = orderService.getAll(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{serial}")
    public ResponseEntity<?> getOrderDetailBySerialNum(@PathVariable String serial) {
        Long userId = getUserId();
        Orders order = orderService.getBySerial (userId, serial);
        if (order == null) {
            return new ResponseEntity<>("Không tìm thấy đơn hàng của bạn",HttpStatus.BAD_REQUEST);
        }

        List<OrderDetails> orderDetails = orderDetailService.getByOrderId(order.getId());
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

}
