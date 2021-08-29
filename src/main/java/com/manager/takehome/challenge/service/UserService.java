package com.manager.takehome.challenge.service;

import com.manager.takehome.challenge.models.User;

import java.util.List;

public interface UserService {

  List<User> findAllActiveUsers();

}
