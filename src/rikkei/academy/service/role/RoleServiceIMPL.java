package rikkei.academy.service.role;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.service.role.IRoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService {
    public static List<Role> roleList = new ArrayList<>();
    static {
        roleList.add(new Role(1,RoleName.USER));
        roleList.add(new Role(2,RoleName.SHOP));
        roleList.add(new Role(3,RoleName.DRIVER));
        roleList.add(new Role(4,RoleName.ADMIN));
    }
    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName name) {
        for (int i = 0; i < roleList.size(); i++) {
            if (name==roleList.get(i).getName()) {
                return roleList.get(i);
            }
        }
        return null;
    }

    @Override
    public Role findByRoleName(RoleName roleName) {
        for (Role role : roleList) {
            if (role.getName() == roleName) {
                return role;
            }
        }
        return null;
    }

}
