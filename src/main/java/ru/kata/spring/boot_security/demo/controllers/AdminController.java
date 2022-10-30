package ru.kata.spring.boot_security.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

/**
 * @ In the name of Allah, most gracious and most merciful! 15.10.2022
 */
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("admin")
    public String showAllUsers(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", userService.findUserById(user.getId()));
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "admin";
    }

    @PostMapping("admin/add")
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "user";
    }

    @PutMapping("admin/{id}")
    public String update(@PathVariable("id") Long id,
                         @ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
