package com.example.movies_7_1.repository;

import com.example.movies_7_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    boolean existsByUsername(String username);
//}