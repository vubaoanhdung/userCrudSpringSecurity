package com.damon.vu.simplecrud.Service;

import com.damon.vu.simplecrud.Entity.RoleEntity;
import com.damon.vu.simplecrud.Entity.UserDto;
import com.damon.vu.simplecrud.Entity.UserEntity;
import com.damon.vu.simplecrud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleServiceImpl roleService; // note

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity save(UserDto userDto, String roleAssigned) {
        RoleEntity role;
        UserEntity newUser = userDto.getUserFromDto();
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<RoleEntity> roles = new HashSet<>();

        role = roleService.findByName("USER");
        roles.add(role);

        // TODO: business logics for more complex roles. Have only 1 role for now
        if (roleAssigned != null) {
            role = roleService.findByName(roleAssigned.toUpperCase());
            roles.add(role);
        }

        // setting/saving roles for user
        newUser.setRoles(roles);
        return this.userRepository.save(newUser);
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(user -> users.add(user));
        return users;
    }

    @Override
    public UserEntity findOne(String email) {
        return this.userRepository.findUserByEmail(email);
    }

}
