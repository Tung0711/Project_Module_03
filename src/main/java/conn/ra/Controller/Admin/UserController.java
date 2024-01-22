package conn.ra.Controller.Admin;

import conn.ra.Model.Entity.Role;
import conn.ra.Model.Entity.User;
import conn.ra.Service.Auth.RoleService;
import conn.ra.Service.Auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUser(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "username", name = "sort") String sort,
            @RequestParam(defaultValue = "asc", name = "sortBy") String sortBy
    ) {
        Pageable pageable;
        if (sortBy.equals("asc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        }
        Page<User> users = userService.getAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateStatusUser(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("Người dùng không tồn tại");
        } else {
            user.setStatus(!user.getStatus ());
            userService.save(user);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        List<Role> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("users/search")
    public ResponseEntity<?> findByName(@RequestParam String query) {
        Optional<User> user = userService.findByUserName (query);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
