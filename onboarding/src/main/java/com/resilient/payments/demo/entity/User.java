package com.resilient.payments.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "onboarding")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
  @Column(name = "user_id")
  @SequenceGenerator(
      name = "user_id_sequence",
      sequenceName = "onboarding.user_id_sequence",
      allocationSize = 1)
  private Long userId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "user_email")
  private String userEmail;

  @Column(name = "password")
  private String password;
}
