package com.resilient.payments.demo.service.impl;

import com.resilient.payments.demo.dao.OnboardingDao;
import com.resilient.payments.demo.entity.User;
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
    try {

      User user = onboardingDao.getUserByUserName(username);
    } catch (Exception ex) {
      throw new UsernameNotFoundException("User not found");
    }
    return null;
  }
}
