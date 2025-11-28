package com.example.domain.repository;

import com.example.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Short> {

    Optional<User> findBySsn(String ssn);
}
