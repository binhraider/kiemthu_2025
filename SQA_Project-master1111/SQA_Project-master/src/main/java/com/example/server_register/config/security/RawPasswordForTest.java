//package com.example.server_register.config.security;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class RawPasswordForTest implements PasswordEncoder {
//    @Override
//    public String encode(CharSequence rawPassword) {
//        return rawPassword.toString();
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        return rawPassword.toString().equals(encodedPassword);
//    }
//
//    @Override
//    public boolean upgradeEncoding(String encodedPassword) {
//        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
//    }
//}
