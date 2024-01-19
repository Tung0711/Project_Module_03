package conn.ra.Service.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.ERole;
import conn.ra.Model.Entity.Role;
import conn.ra.Model.Entity.User;
import conn.ra.Repository.RoleRepository;
import conn.ra.Repository.UserRepository;
import conn.ra.Security.User_principal.UserPrincipal;
import conn.ra.Security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User register(User user) {
        // mã hóa mật khẩu
        user.setPassword ( new BCryptPasswordEncoder ().encode ( user.getPassword () ) );
        // tìm quyền theo tên để set default register sẽ có quyền là user
        Role role = roleRepository.findByRoleName ( ERole.valueOf ( "ROLE_USER" ) );
        Set<Role> roles = new HashSet<> ();
        roles.add ( role );
        user.setRoles ( roles );
        return userRepository.save ( user );
    }

    @Override
    public UserResponse login(UserLogin userLogin) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.
                    authenticate ( new UsernamePasswordAuthenticationToken ( userLogin.getUserName (), userLogin.getPassword () ) );
        } catch (AuthenticationException exception) {
            throw new RuntimeException ( "username or password fail" );
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal ();
        // tạo ra 1 token
        String token = jwtProvider.generateToken ( userPrincipal );
        // covert sang doi tung UserResponse
        return UserResponse.builder ().
                fullName ( userPrincipal.getUser ().getFullName () )
                .id ( userPrincipal.getUser ().getId () ).token ( token ).
                roles ( userPrincipal.getAuthorities ().stream ().map ( GrantedAuthority::getAuthority ).collect ( Collectors.toSet () ) ).
                build ();
    }
}