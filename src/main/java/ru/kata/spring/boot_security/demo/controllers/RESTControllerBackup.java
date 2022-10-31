//package ru.kata.spring.boot_security.demo.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.models.Role;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.services.RoleService;
//import ru.kata.spring.boot_security.demo.services.UserService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @ In the name of Allah, most gracious and most merciful! 31.10.2022
// */
//@RestController
//@RequestMapping("admin")
//@RequiredArgsConstructor
//public class RESTControllerBackup {
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//
//    @GetMapping("users")
//    public ResponseEntity<List<User>> showAllUsers() {
//        List<User> userList = userService.findAllUsers();
//        return userList != null && !userList.isEmpty() ?
//                new ResponseEntity<>(userList, HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @GetMapping("users/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        User user = userService.findUserById(id);
//        return user != null ?
//                new ResponseEntity<>(user, HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//
//    @PostMapping("users")
//    public ResponseEntity<?> createNewUser(@RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String error = getErrorsFromBindingResult(bindingResult);
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }
//        try {
//            userService.addUser(user);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        } catch (DataIntegrityViolationException e) {
//            throw new IllegalStateException("User with such email exists");
//        }
//    }
//
//    @PatchMapping("users/{id}")
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//        userService.updateUser(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//
//    @DeleteMapping("users/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(("User was deleted."), HttpStatus.OK);
//    }
//
//    @GetMapping("authorities")
//    public ResponseEntity<List<Role>> getAllRoles() {
//        List<Role> roles = roleService.findAllRoles();
//        List<Role> newRolesArray = roles.subList(0, 2);
//        return new ResponseEntity<>(newRolesArray, HttpStatus.OK);
//    }
//
//    private String getErrorsFromBindingResult(BindingResult bindingResult) {
//        return bindingResult.getFieldErrors()
//                .stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.joining("; "));
//    }
//}
//
