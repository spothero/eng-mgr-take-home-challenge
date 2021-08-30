package com.manager.takehome.challenge.service;

import com.manager.takehome.challenge.models.User;
import com.manager.takehome.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<User> findAllActiveUsers() {
    return userRepository.findAllActiveUser();
  }

  @Override
  public boolean checkIfUserExists(int id ) {
    return userRepository.checkIfUserExists(id);
  }

}
