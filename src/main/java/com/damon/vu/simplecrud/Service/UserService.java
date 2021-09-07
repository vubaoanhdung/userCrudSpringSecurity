package com.damon.vu.simplecrud.Service;

import com.damon.vu.simplecrud.Entity.UserDto;
import com.damon.vu.simplecrud.Entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity save(UserDto userDto, String roleAssigned);
    List<UserEntity> findAll();
    UserEntity findOne(String email);
    void deleteByEmail(String email);
    void deleteAll();
}
