package com.example.domain.utils;

import com.example.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordMigrationRunner {

    @Bean
    public CommandLineRunner runPasswordMigration(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.findAll().forEach(u -> {
                if (!u.getPassword().startsWith("$2")) {
                    u.setPassword(encoder.encode(u.getPassword()));
                    repo.save(u);
                }
            });
        };
    }
}
