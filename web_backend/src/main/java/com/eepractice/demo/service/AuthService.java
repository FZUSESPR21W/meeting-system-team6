package com.eepractice.demo.service;

import com.eepractice.demo.bean.User;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AuthService {

    Optional<User> login(String username, String password,String role);

    boolean register(String username , String password);

}
