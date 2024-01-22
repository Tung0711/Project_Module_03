package conn.ra.Service.ShoppingCart;

import conn.ra.Model.Dto.Request.ShoppingCartRequest;
import conn.ra.Model.Entity.Product;
import conn.ra.Model.Entity.ShoppingCart;
import conn.ra.Model.Entity.User;
import conn.ra.Repository.ShoppingCartRepository;
import conn.ra.Service.Auth.UserService;
import conn.ra.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public List<ShoppingCart> getAll(Long userId) {
        return shoppingCartRepository.findByUsers(userId);
    }

    @Override
    public ShoppingCart add(ShoppingCartRequest shoppingCartRequest, Long userId) {

        Product product = productService.findById(shoppingCartRequest.getProductId());
        User user = userService.findById(userId);

        if(product == null) {
            throw new RuntimeException("Không tồn tại sản phẩm!");
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .orderQuantity (shoppingCartRequest.getQuantity())
                .product(product)
                .user(user)
                .build();
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findById(int id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void delete(int id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public ShoppingCart findByProductId(Long userId, Long productId) {
        return shoppingCartRepository.findByUserAndProduct(userId, productId);
    }
}
