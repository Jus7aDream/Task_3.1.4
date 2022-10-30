package ru.kata.spring.boot_security.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repo.RoleRepo;

import java.util.List;
import java.util.Optional;

/**
 * @ In the name of Allah, most gracious and most merciful! 05.10.2022
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;


    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
