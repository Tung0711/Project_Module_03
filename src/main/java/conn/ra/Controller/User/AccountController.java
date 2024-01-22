package conn.ra.Controller.User;

import conn.ra.Model.Dto.Request.AddressRequest;
import conn.ra.Model.Dto.Request.PasswordRequest;
import conn.ra.Model.Dto.Request.UserRegister;
import conn.ra.Model.Entity.Address;
import conn.ra.Model.Entity.User;
import conn.ra.Security.User_principal.UserPrincipal;
import conn.ra.Service.Address.AddressService;
import conn.ra.Service.Auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddressService addressService;

    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getUser().getId();
    }

    @GetMapping("")
    public ResponseEntity<User> getAccount() {
        Long id = getUserId();
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<User> updateAccount(@RequestBody UserRegister userRegister) {
        Long id = getUserId();
        User user = userService.updateAcc(userRegister, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordRequest passwordRequest) {
        Long id = getUserId();
        User user = userService.findById(id);
        String oldPassword = user.getPassword();

        boolean isPasswordMatch = passwordEncoder.matches(passwordRequest.getOldPass(), oldPassword);
        if (isPasswordMatch) {
            user.setPassword(passwordEncoder.encode(passwordRequest.getNewPass()));
            userService.save(user);
            return new ResponseEntity<>("Cập nhật mật khẩu thành công", HttpStatus.OK);
        }else {
            throw new RuntimeException("Mật khẩu cũ không chính xác!");
        }
    }

    @PostMapping("/address")
    public ResponseEntity<?> addAddress(@RequestBody AddressRequest addressRequest) {
        Long id = getUserId();
        Address address = addressService.add(addressRequest, id);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long addressId) {
        Long id = getUserId();
        addressService.delete(addressId, id);
        return new ResponseEntity<>("Địa chỉ đã xóa khỏi tài khoản của bạn!" ,HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> findAllAddress() {
        Long id = getUserId();
        List<Address> addresses = addressService.getAll(id);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> findById(@PathVariable("id") Long addressId) {
        Long id = getUserId();
        Address address = addressService.findById(addressId, id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
