package com.eepractice.demo.repository;


import com.eepractice.demo.bean.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {

        Optional<User> findUserByUsername(String username);


}
