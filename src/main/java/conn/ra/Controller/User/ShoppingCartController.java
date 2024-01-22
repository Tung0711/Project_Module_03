package conn.ra.Controller.User;

import conn.ra.Model.Dto.Request.ShoppingCartRequest;
import conn.ra.Model.Entity.ShoppingCart;
import conn.ra.Security.User_principal.UserPrincipal;
import conn.ra.Service.ShoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }

    @GetMapping("")
    public ResponseEntity<List<ShoppingCart>> getAll() {
        Long userId = getUserId();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAll(userId);
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ShoppingCart> add(@RequestBody ShoppingCartRequest shoppingCartRequest) {
        Long userId = getUserId();
        ShoppingCart shoppingCart = shoppingCartService.add(shoppingCartRequest, userId);
        return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateQuantity(@RequestBody int quantity, @PathVariable("id") int id) {
        Long userId = getUserId();
        ShoppingCart shoppingCartUpdate = shoppingCartService.findById(id);
        if(shoppingCartUpdate != null) {
            if(shoppingCartUpdate.getUser().getId().equals(userId)) {
                shoppingCartUpdate.setOrderQuantity (quantity);
                shoppingCartService.save(shoppingCartUpdate);
                return new ResponseEntity<>(shoppingCartUpdate, HttpStatus.OK);
            }else {
                throw new RuntimeException("Bạn không có quyền thay đổi giỏ hàng!");
            }
        } else {
            throw new RuntimeException("Không tồn tại giỏ hàng!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Long userId = getUserId();
        ShoppingCart shoppingCart = shoppingCartService.findByProductId(userId, id);
        if (shoppingCart != null) {
            shoppingCartService.delete(shoppingCart.getId ());
            return new ResponseEntity<>("Sản phẩm đã được xóa khỏi giỏ hàng của bạn", HttpStatus.OK);
        }else {
            throw new RuntimeException("Sản phẩm không tồn tại trong giỏ hàng của bạn");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAll() {
        Long userId = getUserId();
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAll(userId);
        shoppingCarts.forEach(shoppingCart -> shoppingCartService.delete(shoppingCart.getId()));
        return new ResponseEntity<>("Tất cả sản phẩm đã được xóa khỏi giỏ hàng của bạn", HttpStatus.OK);
    }
}
