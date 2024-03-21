package com.refactorizando.postman.example.repository;

import com.refactorizando.postman.example.domain.user.UserApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<UserApp, Long> {
    Optional<UserApp> findByUserName(String userName);

    Boolean existsByUserName(String username);
}
