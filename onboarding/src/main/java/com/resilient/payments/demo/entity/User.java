package com.resilient.payments.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users", schema = "onboarding")
public class User {


    @Id
    private Long userId;

    private String userName;

    private String userEmail;

    private String password;

    private List<String> roles;


}
