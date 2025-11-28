package com.example.domain;

import com.example.domain.dto.PasswordUpdateRequest;
import com.example.domain.dto.UserCreationRequest;
import com.example.domain.dto.UserDTOMapper;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.StringUtils.capitalize;

@Service
public class UserService {
    private final UserRepository userRepo;

    private final UserDTOMapper mapper;

    @Autowired
    public UserService(UserRepository userRepo, UserDTOMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public void createUser(UserCreationRequest req) {
        User user = new User(
                req.firstName(),
                req.lastName(),
                req.ssn(),
                req.password()
        );

        user.setName(User.makeUserName(user));
        userRepo.save(user);
    }

    public void updateUserPassword(PasswordUpdateRequest req) {
        Optional<User> user = userRepo.findById(req.userId());
        if (user.isEmpty()) {
            System.out.println("User not found");
            return;
        }
        user.get().setPassword(req.password());
    }
}
