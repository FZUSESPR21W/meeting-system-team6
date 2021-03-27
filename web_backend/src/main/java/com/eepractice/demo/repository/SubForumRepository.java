package com.eepractice.demo.repository;

import com.eepractice.demo.bean.SubForum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubForumRepository extends CrudRepository<SubForum,Integer> {

    Optional<SubForum> findById(Integer integer);

    @Override
    Iterable<SubForum> findAll();
}
