package com.manager.takehome.challenge.builder;

import com.manager.takehome.challenge.dto.v1.ActiveUsersRetrievalResponse;
import com.manager.takehome.challenge.models.User;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ActiveUsersRetrievalResponseBuilder {


  /**
  * builds an ActiveUsersRetrievalResponse DTO.
  */
  public static ActiveUsersRetrievalResponse buildActiveUsersRetrievalResponse(
      List<User> activeUsers) {
    ActiveUsersRetrievalResponse activeUsersRetrievalResponse = new ActiveUsersRetrievalResponse();
    List<com.manager.takehome.challenge.dto.v1.User> users = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(activeUsers)) {
      for (User activeUser : activeUsers) {
        com.manager.takehome.challenge.dto.v1.User user =
            new com.manager.takehome.challenge.dto.v1.User();
        user.setId(activeUser.getId());
        user.setFirstName(activeUser.getFirstName());
        user.setLastName(activeUser.getLastName());
        user.setEmail(activeUser.getEmailAddress());
        users.add(user);
      }
    }
    activeUsersRetrievalResponse.setActiveUsers(users);
    return activeUsersRetrievalResponse;

  }


}
