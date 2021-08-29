package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.User;

import java.util.List;

public interface UserRepository {

  List<User> findAllActiveUser();
}
