package com.jgos.hotelbooker.repository;

import com.jgos.hotelbooker.entity.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bos on 2017-05-07.
 */
@Repository
public interface UserRepository extends CrudRepository<UserDb, Long> {

    UserDb findByEmail(String email);
}
