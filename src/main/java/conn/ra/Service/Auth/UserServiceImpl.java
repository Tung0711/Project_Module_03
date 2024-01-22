package conn.ra.Service.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Request.UserRegister;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.ERole;
import conn.ra.Model.Entity.Role;
import conn.ra.Model.Entity.User;
import conn.ra.Repository.RoleRepository;
import conn.ra.Repository.UserRepository;
import conn.ra.Security.User_principal.UserPrincipal;
import conn.ra.Security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public User register(UserRegister userRegister) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if(userRepository.existsByUserName (userRegister.getUserName ())) {
            throw new RuntimeException("username is exists");
        }
        // mã hóa mật khẩu
        userRegister.setPassword ( new BCryptPasswordEncoder ().encode ( userRegister.getPassword () ) );
        // tìm quyền theo tên để set default register sẽ có quyền là user
        Set<Role> roles = new HashSet<> ();
        roles.add ( roleService.findByRoleName ( ERole.ROLE_USER ) );
        User user = User.builder()
                .fullName(userRegister.getFullName())
                .userName(userRegister.getUserName())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .address(userRegister.getAddress())
                .status(true)
                .roles(roles)
                .build();
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
        SecurityContextHolder.getContext ().setAuthentication ( authentication );
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal ();
        if (!userPrincipal.getUser ().getStatus ()) {
            throw new RuntimeException ( "your account is blocked" );
        }
        String token = jwtProvider.generateToken ( userPrincipal );
        return UserResponse.builder ()
                .fullName ( userPrincipal.getUser ().getFullName () )
                .token ( jwtProvider.generateToken ( userPrincipal ) )
                .userName ( userLogin.getUserName () )
                .status ( userPrincipal.getUser ().getStatus () ).token ( token ).
                roles ( userPrincipal.getAuthorities ().stream ().map ( GrantedAuthority::getAuthority ).collect ( Collectors.toSet () ) ).
                build ();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll (pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById ( id ).orElse ( null );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById ( id );
    }

    @Override
    public User save(User user) {
        return userRepository.save ( user );
    }

    @Override
    public User updateAcc(UserRegister userRegister, Long id) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if(userRepository.existsByUserName (userRegister.getUserName())) {
            throw new RuntimeException("username is exists");
        }

        Set<Role> roles = findById(id).getRoles();

        User users = User.builder()
                .id(id)
                .fullName(userRegister.getFullName())
                .userName(userRegister.getUserName())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .address(userRegister.getAddress())
                .status(true)
                .roles(roles)
                .build();
        return userRepository.save(users);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName ( userName );
    }
}