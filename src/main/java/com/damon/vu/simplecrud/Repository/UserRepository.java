package com.damon.vu.simplecrud.Repository;

import com.damon.vu.simplecrud.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Transactional
    UserEntity findUserByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
