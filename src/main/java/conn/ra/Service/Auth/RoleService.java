package conn.ra.Service.Auth;

import conn.ra.Model.Entity.ERole;
import conn.ra.Model.Entity.Role;

import java.util.List;

public interface RoleService {
    Role findByRoleName(ERole name);
    List<Role> getAll();
}
