package com.resilient.payments.demo.dao.impl;

import com.resilient.payments.demo.dao.OnboardingDao;
import com.resilient.payments.demo.entity.User;
import com.resilient.payments.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnboardingDaoImpl implements OnboardingDao {

  @Autowired UserRepository userRepository;

  @Override
  public User getUserByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }
}
