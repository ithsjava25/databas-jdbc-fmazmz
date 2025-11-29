package com.example;


import com.example.domain.UserService;
import com.example.domain.dto.PasswordUpdateRequest;
import com.example.domain.dto.UserCreationRequest;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    public void createUser() {
        String password = "password";
        UserCreationRequest req = new UserCreationRequest(
                "Test",
                "User",
                "990905-1234",
                password
        );

        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        userService.createUser(req);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepo).save(captor.capture());

        User user = captor.getValue();

        assertThat(user.getFirstName()).isEqualTo("Test");
        assertThat(user.getLastName()).isEqualTo("User");
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getSsn()).isEqualTo("990905-1234");
        assertThat(user.getName()).isEqualTo(User.makeUserName(user));
    }

    @Test
    public void deleteUser() {
        Integer userId = 10;
        User mockUser = new User("Test", "User", "990905-1234", "encodedPassword");
        mockUser.setUserId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser));

        userService.deleteUser(userId);
        verify(userRepo).delete(mockUser);
    }

    @Test
    public void updateUserPassword() {
        Integer userId = 10;
        User mockUser = new User("Test", "User", "990905-1234", "encodedPassword");
        mockUser.setUserId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.encode("encodedPassword2")).thenReturn("encodedPassword2");

        userService.updateUserPassword(new PasswordUpdateRequest(userId, "encodedPassword2"));
        verify(userRepo).save(mockUser);

        assertThat(mockUser.getPassword()).isEqualTo("encodedPassword2");
    }
}
