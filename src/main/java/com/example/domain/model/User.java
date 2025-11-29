package com.example.domain.model;

import jakarta.persistence.*;

import static org.springframework.util.StringUtils.capitalize;

@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

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
    public User(String firstName, String lastName, String ssn, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.password = password;
    }


    public static String makeUserName(User user) {
        String first = user.getFirstName() == null ? "" : user.getFirstName().trim();
        String last  = user.getLastName()  == null ? "" : user.getLastName().trim();

        String p1 = first.length() >= 3 ? first.substring(0, 3) : first;
        String p2 = last.length()  >= 3 ? last.substring(0, 3)  : last;

        return capitalize(p1) + capitalize(p2);
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
