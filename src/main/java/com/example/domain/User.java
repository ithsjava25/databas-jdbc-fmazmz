package com.example.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "ssn")
    private String ssn;


    public User() {}
    public User(int userId, String name, String password, String firstName, String lastName, String ssn) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsn() {
        return ssn;
    }
}
