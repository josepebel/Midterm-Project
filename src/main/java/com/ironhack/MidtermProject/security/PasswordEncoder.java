package com.ironhack.MidtermProject.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class PasswordEncoder {

    public static String encryptPassword(String plainPassword) {
        org.springframework.security.crypto.password.PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();
        return passwordEncoder.encode(plainPassword);
    }

    public static void main(String[] args) {
        org.springframework.security.crypto.password.PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
    }
}
