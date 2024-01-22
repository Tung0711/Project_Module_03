package conn.ra.Service.Auth;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Request.UserRegister;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.ERole;
import conn.ra.Model.Entity.Role;
import conn.ra.Model.Entity.User;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public String register(UserRegister userRegister) {
        if(userRepository.existsByUserName (userRegister.getUserName ())) {
            throw new RuntimeException("username is exists");
        }

        Set<Role> role = new HashSet<>();
        role.add(roleService.findByRoleName(ERole.ROLE_USER));
        User users = User.builder()
                .fullName(userRegister.getFullName())
                .userName (userRegister.getUserName ())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .email(userRegister.getEmail())
                .phone(userRegister.getPhone())
                .address(userRegister.getAddress())
                .created(new Date (new java.util.Date().getTime()))
                .status(true)
                .roles (role)
                .build();
        userRepository.save(users);
        return "Success";
    }

    @Override
    public UserResponse login(UserLogin userLogin) {
        Authentication authentication;
        try {
            authentication = authenticationProvider.
                    authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(),userLogin.getPassword()));
        } catch (AuthenticationException exception){
            throw new RuntimeException("username or password sai cmnr");
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        // tạo ra 1 token
        String token = jwtProvider.generateToken(userPrincipal);
        // covernt sang doi tung UserResoine
        return UserResponse.builder().
                fullName(userPrincipal.getUser().getFullName())
                .id(userPrincipal.getUser().getId()).token(token).
                roles(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())).
                build();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll ( pageable );
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
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd/MM/yyyy" );

        if (userRepository.existsByUserName ( userRegister.getUserName () )) {
            throw new RuntimeException ( "username is exists" );
        }

        Set<Role> roles = findById ( id ).getRoles ();

        User users = User.builder ()
                .id ( id )
                .fullName ( userRegister.getFullName () )
                .userName ( userRegister.getUserName () )
                .password ( passwordEncoder.encode ( userRegister.getPassword () ) )
                .email ( userRegister.getEmail () )
                .phone ( userRegister.getPhone () )
                .address ( userRegister.getAddress () )
                .status ( true )
                .roles ( roles )
                .build ();
        return userRepository.save ( users );
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName ( userName );
    }
}