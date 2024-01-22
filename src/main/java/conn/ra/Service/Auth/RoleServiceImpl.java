package conn.ra.Service.Auth;

import conn.ra.Model.Entity.ERole;
import conn.ra.Model.Entity.Role;
import conn.ra.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRoleName(ERole name) {
        Role role = roleRepository.findByRoleName ( name ).orElseThrow ( () -> new RuntimeException ( "role not found" ) );
        return role;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll ();
    }
}
