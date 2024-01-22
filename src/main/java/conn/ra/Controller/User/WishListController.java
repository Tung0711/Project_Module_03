package conn.ra.Controller.User;

import conn.ra.Model.Entity.WishList;
import conn.ra.Service.WishList.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static conn.ra.Controller.User.AccountController.getUserId;

@RestController
@RequestMapping("/v1/user/wish-list")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @PostMapping("")
    public ResponseEntity<WishList> add(@RequestBody Long productId) {
        Long userId = getUserId ();
        WishList wishList = wishListService.add ( userId, productId );
        return new ResponseEntity<> ( wishList, HttpStatus.CREATED );
    }

    @GetMapping("")
    public ResponseEntity<List<WishList>> getAll() {
        Long userId = getUserId ();
        List<WishList> wishLists = wishListService.getAll ( userId );
        return new ResponseEntity<> ( wishLists, HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long wishListId) {
        Long userId = getUserId ();
        wishListService.delete ( wishListId, userId );
        return new ResponseEntity<> ( "Xóa thành công!", HttpStatus.OK );
    }
}
