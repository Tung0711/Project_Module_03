package conn.ra.Service.WishList;

import conn.ra.Model.Entity.Product;
import conn.ra.Model.Entity.User;
import conn.ra.Model.Entity.WishList;
import conn.ra.Repository.WishListRepository;
import conn.ra.Service.Auth.UserService;
import conn.ra.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService{
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WishListRepository wishListRepository;

    @Override
    public WishList add(Long userId, Long productId) {
        User user = userService.findById(userId);

        Product product = productService.findById(productId);

        if (product == null) {
            throw new RuntimeException("không tồn tại sản phẩm");
        }

        WishList wishList = WishList.builder()
                .user(user)
                .product(product)
                .build();
        return wishListRepository.save(wishList);
    }

    @Override
    public List<WishList> getAll(Long userId) {
        return wishListRepository.getAllByUserId(userId);
    }

    @Override
    public void delete(Long wishListId, Long userId) {
        wishListRepository.deleteByIdAndUserId(wishListId, userId);
    }
}
