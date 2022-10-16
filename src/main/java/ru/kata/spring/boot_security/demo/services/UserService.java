package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

/**
 * @ In the name of Allah, most gracious and most merciful! 12.10.2022
 */
public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUserByUsername(String email);
}
