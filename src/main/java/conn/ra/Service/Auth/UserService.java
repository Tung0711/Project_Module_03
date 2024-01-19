package conn.ra.Service.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.User;

public interface UserService {
    User register(User user);
    UserResponse login(UserLogin userLogin);
}
