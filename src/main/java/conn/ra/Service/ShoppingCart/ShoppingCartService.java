package conn.ra.Service.ShoppingCart;

import conn.ra.Model.Dto.Request.ShoppingCartRequest;
import conn.ra.Model.Entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> getAll(Long userId);
    ShoppingCart add(ShoppingCartRequest shoppingCartRequest, Long userId);
    ShoppingCart findById(int id);

    ShoppingCart save(ShoppingCart shoppingCart);

    void delete(int id);

    ShoppingCart findByProductId(Long userId, Long productId);
}
