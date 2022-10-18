package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

/**
 * @ In the name of Allah, most gracious and most merciful! 12.10.2022
 */
public interface RoleService {
    Role getRoleByName(String name);
    List<Role> getAllRoles();

}
