package conn.ra.Controller.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
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
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid User user){
        User userNew = userService.register(user);
        if(userNew != null){
            return new ResponseEntity<>("register successful !",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("register fail",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLogin userLogin){
        UserResponse userResponse = userService.login(userLogin);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
}
