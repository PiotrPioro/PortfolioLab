package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.repository.RoleRepository;

@AllArgsConstructor
@Repository
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findRoleByRole(String role){
        return roleRepository.findByRole(role);
    }
}
