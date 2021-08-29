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
    List<com.manager.takehome.challenge.dto.v1.User> usersDtoList = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(activeUsers)) {
      for (User activeUser : activeUsers) {
        com.manager.takehome.challenge.dto.v1.User userDto =
            new com.manager.takehome.challenge.dto.v1.User();
        userDto.setId(activeUser.getId());
        userDto.setFirstName(activeUser.getFirstName());
        userDto.setLastName(activeUser.getLastName());
        userDto.setEmail(activeUser.getEmailAddress());
        usersDtoList.add(userDto);
      }
    }
    activeUsersRetrievalResponse.setActiveUsers(usersDtoList);
    return activeUsersRetrievalResponse;

  }


}
