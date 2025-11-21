package com.resilient.payments.demo.dao;

import com.resilient.payments.demo.entity.User;

public interface OnboardingDao {

  public User getUserByUserName(String userName);
}
