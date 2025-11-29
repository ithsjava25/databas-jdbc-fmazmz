package com.example.domain;

import com.example.domain.dto.PasswordUpdateRequest;
import com.example.domain.dto.UserCreationRequest;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo,  PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserCreationRequest req) {
        User user = new User(
                req.firstName(),
                req.lastName(),
                req.ssn(),
                passwordEncoder.encode(req.password())
        );

        user.setName(User.makeUserName(user));
        userRepo.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepo.findById(userId)
                .ifPresentOrElse(
                        userRepo::delete,
                        () -> System.out.println("User not found")
                );
    }

    @Transactional
    public void updateUserPassword(PasswordUpdateRequest req) {
        Optional<User> user = userRepo.findById(req.userId());
        if (user.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        user.get().setPassword(passwordEncoder.encode(req.password()));
        userRepo.save(user.get());
    }
}
