package conn.ra.Service.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Request.UserRegister;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User register(UserRegister userRegister);
    UserResponse login(UserLogin userLogin);
    Page<User> getAll(Pageable pageable);
    User findById(Long id);
    void delete(Long id);
    User save(User user);
    User updateAcc(UserRegister userRegister,Long id);
    Optional<User> findByUserName(String userName);
}
