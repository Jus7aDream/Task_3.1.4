package ru.kata.spring.boot_security.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Optional;

/**
 * @ In the name of Allah, most gracious and most merciful! 29.09.2022
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    void deleteUserById(Long id);

    Optional<User> findUserById(Long id);
}
