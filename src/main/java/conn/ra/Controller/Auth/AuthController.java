package conn.ra.Controller.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Request.UserRegister;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.User;
import conn.ra.Service.Auth.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserRegister userRegister){
        return new ResponseEntity<>(userService.register(userRegister),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserLogin userLogin){
        UserResponse userResponse = userService.login(userLogin);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
