package conn.ra.Security.User_principal;

import conn.ra.Model.Entity.User;
import conn.ra.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName ( username );
        if (userOptional.isPresent ()) {
            User user = userOptional.get ();
            UserPrincipal userPrincipal = UserPrincipal.builder ().
                    user ( user ).authorities ( user.getRoles ().stream ().map (
                            item -> new SimpleGrantedAuthority ( item.getRoleName ().name () )
                    ).toList () ).build ();
            return userPrincipal;
        }
        return null;
    }
}
