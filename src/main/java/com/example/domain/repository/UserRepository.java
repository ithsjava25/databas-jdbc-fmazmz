package com.example.domain.repository;

import com.example.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findBySsn(String ssn);

    Optional<User> findByName(String username);
}
