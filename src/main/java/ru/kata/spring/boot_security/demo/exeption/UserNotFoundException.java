package ru.kata.spring.boot_security.demo.exeption;

/**
 * @ In the name of Allah, most gracious and most merciful! 06.10.2022
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

}
