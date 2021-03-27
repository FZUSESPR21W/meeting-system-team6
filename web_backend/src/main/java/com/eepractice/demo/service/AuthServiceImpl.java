package com.eepractice.demo.service;

import com.eepractice.demo.bean.User;
import com.eepractice.demo.properties.RoleProperty;
import com.eepractice.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleProperty roleProperty;

    @Override
    public Optional<User> login(String username, String password,String role) {
        // username password 为空
        if (username.trim().equals("") || password.trim().equals("")) return Optional.empty();
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        // user不存在
        if (!userOptional.isPresent()) return Optional.empty();
        User user = userOptional.get();
        // 存在，密码错误
        if(!user.getPassword().equals(password) || !user.getRole().equals(role)) return Optional.empty();
        // 用户登录成功
        return Optional.of(user);
    }

    @Override
    public boolean register(String username,String password)
    {
        //用户名或密码为空
        if (username.trim().equals("") || password.trim().equals(""))
            return false;
        //用户名已经存在
        if (userRepository.findUserByUsername(username).isPresent()){
            return false;
        }else if(password.length() <= 3){
            return false;
        }else{
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(roleProperty.role_user);
            user.setJoinedSubForums(new ArrayList<>());
            userRepository.save(user);
            return true;
        }
    }
}
