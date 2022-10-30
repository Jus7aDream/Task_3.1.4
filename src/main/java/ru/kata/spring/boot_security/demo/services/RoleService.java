package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
import java.util.Optional;

/**
 * @ In the name of Allah, most gracious and most merciful! 12.10.2022
 */
public interface RoleService {
    List<Role> findAllRoles();

    Optional<Role> findRoleById(Long id);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

}
