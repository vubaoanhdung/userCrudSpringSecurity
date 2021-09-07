package com.damon.vu.simplecrud.Service;

import com.damon.vu.simplecrud.Entity.RoleEntity;
import com.damon.vu.simplecrud.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findByName(String name) {
        RoleEntity role = this.roleRepository.findRoleByName(name);
        return role;
    }
}
