package com.manager.takehome.challenge.integrationTests;

import com.manager.takehome.challenge.controller.WorkedHoursController;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordRequest;
import com.manager.takehome.challenge.models.User;
import com.manager.takehome.challenge.models.WorkedHours;
import com.manager.takehome.challenge.repository.WorkedHoursRepository;
import com.manager.takehome.challenge.service.UserService;
import com.manager.takehome.challenge.service.WorkedHoursService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import testUtils.TestConstants;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkedHoursControllerIT {

  @Autowired
  private WorkedHoursController workedHoursController;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WorkedHoursService workedHoursService;

  @MockBean
  private UserService userService;

  @MockBean
  private WorkedHoursRepository workedHoursRepository;

  @Test
  public void contextLoads() throws Exception {
    assertThat(workedHoursController).isNotNull();
  }

  @Test
  public void testFetchingWorkedHoursForValidUser() throws Exception {

    List<WorkedHours>  listOfWorkedHours = new ArrayList<>();
    WorkedHours workedHours =new WorkedHours.WorkedHoursBuilder()
        .withUserId(123)
        .withHours(BigDecimal.valueOf(5.5))
        .withDate(LocalDate.of(2020, 1, 8))
        .build();

    listOfWorkedHours.add(workedHours);

    when(userService.checkIfUserExists(123)).thenReturn(true);
    when(workedHoursService.findAllWorkedHoursByUser(123)).thenReturn(listOfWorkedHours);


    this.mockMvc.perform(get("/v1/users/123/worked_hours"))
        //.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.worked_hours[0].id")
            .value(123))
        .andExpect(jsonPath("$.worked_hours[0].date")
            .value("2020-01-08"))
        .andExpect(jsonPath("$.worked_hours[0].hours")
            .value(5.5));
  }

  @Test
  public void testFetchingWorkedHoursForInValidUser() throws Exception {

    //123 is an invalid user
    when(userService.checkIfUserExists(123)).thenReturn(false);

    this.mockMvc.perform(get("/v1/users/123/worked_hours"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error.error_code").value(404))
        .andExpect(jsonPath("$.error.error_message")
            .value(TestConstants.NO_USER));
  }

  @Test
  public void testPostingWorkedHoursForValidUserAndValidDate() throws Exception {


    when(userService.checkIfUserExists(5)).thenReturn(true);

    when(workedHoursService.checkRecordedHoursForDateByUser(5,
        LocalDate.of(2021, 8, 15))).thenReturn(false);

    when(workedHoursService.saveWorkedHoursByUser(any(Integer.class), any(WorkedHours.class))).thenReturn(1);

    this.mockMvc.perform(post("/v1/users/5/worked_hours")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"date\": \"2021-08-15\", \"hours\": 15.5}")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.post_success").value(true))
        .andDo(print());
  }

  @Test
  public void testPostingWorkedHoursForInValidUser() throws Exception {

    //123 is an invalid user
    when(userService.checkIfUserExists(123)).thenReturn(false);

    this.mockMvc.perform(post("/v1/users/123/worked_hours")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"date\": \"2021-08-15\", \"hours\": 15.5}"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error.error_code").value(404))
        .andExpect(jsonPath("$.error.error_message")
            .value(TestConstants.NO_USER))
        .andDo(print());
  }

  @Test
  public void testPostingWorkedHoursForInvalidWorkedHours() throws Exception {

    //123 is a valid user
    when(userService.checkIfUserExists(123)).thenReturn(true);

    //hours for userId 123 for 2021/8/15 were already recorded
    when(workedHoursService.checkRecordedHoursForDateByUser(123,
        LocalDate.of(2021, 8, 15))).thenReturn(true);

    this.mockMvc.perform(post("/v1/users/123/worked_hours")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"date\": \"2021-08-15\", \"hours\": 15.5}"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isPreconditionFailed())
        .andExpect(jsonPath("$.error.error_code").value(412))
        .andExpect(jsonPath("$.error.error_message")
            .value(TestConstants.HOURS_ALREADY_RECORDED))
        .andDo(print());
  }

  @Test
  public void testFailureOfPostingWorkedHoursForValidUserAndValidDate() throws Exception {

    // 5 is a valid user
    when(userService.checkIfUserExists(5)).thenReturn(true);

    // hours not recorded yet for user 5 for 2021/8/15
    when(workedHoursService.checkRecordedHoursForDateByUser(5,
        LocalDate.of(2021, 8, 15))).thenReturn(false);

    //no rows were inserted into the DB. Reason unknown at this point
    when(workedHoursService.saveWorkedHoursByUser(any(Integer.class), any(WorkedHours.class))).thenReturn(0);

    this.mockMvc.perform(post("/v1/users/5/worked_hours")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"date\": \"2021-08-15\", \"hours\": 15.5}")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.post_success").value(false))
        .andExpect(jsonPath("$.message").value(TestConstants.POST_HOURS_FAILED))
        .andDo(print());
  }
}
