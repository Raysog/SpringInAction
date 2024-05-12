package com.rayzog.repetit.dao;

import com.rayzog.repetit.testTask.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
