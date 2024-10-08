package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.exception.AppException;
import vn.hoidanit.laptopshop.exception.ErrorCode;
import vn.hoidanit.laptopshop.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role FindByName(String Name) {
        List<Role> roles = roleRepository.findByName(Name);
        if (roles.isEmpty()) {
            throw new RuntimeException("Role not found");
        } else {
            return roles.get(0);
        }
    }

    public Role CreateAdminRole() {
        if (roleRepository.findByName("Admin").isEmpty()) {
            Role role = new Role();
            role.setName("Admin");
            role.setDescription("Admin role");
            return roleRepository.save(role);
        } else {
            throw new AppException(ErrorCode.Role_Is_EXIST);
        }
    }
}
