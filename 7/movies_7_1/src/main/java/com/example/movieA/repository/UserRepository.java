package com.example.movieA.repository;


import com.example.movieA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//primary key is long in table
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByUsername(String username);
//}