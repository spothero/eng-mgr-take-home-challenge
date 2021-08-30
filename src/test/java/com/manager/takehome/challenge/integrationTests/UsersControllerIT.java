package com.manager.takehome.challenge.integrationTests;

import com.manager.takehome.challenge.controller.UsersController;
import com.manager.takehome.challenge.models.User;
import com.manager.takehome.challenge.repository.UserRepository;
import com.manager.takehome.challenge.service.UserService;
import com.manager.takehome.challenge.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerIT {

  @Autowired
  private UsersController usersController;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void contextLoads() throws Exception {
    assertThat(usersController).isNotNull();
  }

  @Test
  public void defaultTest() throws Exception {
    this.mockMvc.perform(get("/v1/users"))
        .andExpect(status().isOk());
  }


  @Test
  public void testResponse() throws Exception {
    List<User> activeUsers = new ArrayList<>();
    User activeUser = new User.UserBuilder()
        .withId(123)
        .withManagerId(2)
        .withFirstName("Leah")
        .withLastName("Fleming")
        .withEmailAddress("lfleming@hotmail.com")
        .withIsActive(true)
        .build();
    activeUsers.add(activeUser);

    when(userService.findAllActiveUsers()).thenReturn(activeUsers);
    this.mockMvc.perform(get("/v1/users"))
        //.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.active_users[0].id")
            .value(123))
        .andExpect(jsonPath("$.active_users[0].first_name")
            .value("Leah"))
        .andExpect(jsonPath("$.active_users[0].last_name")
            .value("Fleming"))
        .andExpect(jsonPath("$.active_users[0].email")
            .value("lfleming@hotmail.com"));
  }
}
