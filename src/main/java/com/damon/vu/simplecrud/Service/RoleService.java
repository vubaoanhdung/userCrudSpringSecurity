package com.damon.vu.simplecrud.Service;

import com.damon.vu.simplecrud.Entity.RoleEntity;

public interface RoleService {
    RoleEntity findByName(String name);
}
