package ru.kata.spring.boot_security.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @ In the name of Allah, most gracious and most merciful! 05.10.2022
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotEmpty(message = "Username should not be empty")
    @Size(min = 2, max = 255, message = "Username should be between 2 and 255 characters")
    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        if (name.equals("ROLE_ADMIN")) {
            return "ADMIN";
        } else if (name.equals("ROLE_USER")) {
            return "USER";
        }
        return name;
    }
}
