package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.dao.OnboardingDao;
import com.resilient.payments.demo.entity.User;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired OnboardingDao onboardingDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = onboardingDao.getUserByUserName(username);
    if (Objects.nonNull(user)) {
      return org.springframework.security.core.userdetails.User.builder()
          .username(user.getUserName())
          .password(user.getPassword())
          .build();
    }
    throw new UsernameNotFoundException("User not found with username: " + username);
  }
}
