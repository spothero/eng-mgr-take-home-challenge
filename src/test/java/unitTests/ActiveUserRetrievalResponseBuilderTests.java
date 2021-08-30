package unitTests;

import com.manager.takehome.challenge.builder.ActiveUsersRetrievalResponseBuilder;
import com.manager.takehome.challenge.dto.v1.ActiveUsersRetrievalResponse;
import com.manager.takehome.challenge.models.User;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ActiveUserRetrievalResponseBuilderTests {

  @Test
  public void testActiveUsersRetrievalResponseNotNull() {
    List<User>  activeUsers = null;
    ActiveUsersRetrievalResponse activeUsersRetrievalResponse =
    ActiveUsersRetrievalResponseBuilder.buildActiveUsersRetrievalResponse(activeUsers);
    assertNotNull(activeUsersRetrievalResponse);
  }

  @Test
  public void testListOfActiveUsersNotNull() {
    List<User>  activeUsers = null;
    ActiveUsersRetrievalResponse activeUsersRetrievalResponse =
        ActiveUsersRetrievalResponseBuilder.buildActiveUsersRetrievalResponse(activeUsers);
    assertNotNull(activeUsersRetrievalResponse.getActiveUsers());
  }

  @Test
  public void testActiveUsersRetrievalResponseNotNullWhenNoActiveUsers() {
    List<User>  activeUsers = new ArrayList<>();
    ActiveUsersRetrievalResponse activeUsersRetrievalResponse =
        ActiveUsersRetrievalResponseBuilder.buildActiveUsersRetrievalResponse(activeUsers);
    assertNotNull(activeUsersRetrievalResponse);
  }


  @Test
  public void testListOfActiveUsersNotNullWhenNoActiveUsers() {
    List<User>  activeUsers = null;
    ActiveUsersRetrievalResponse activeUsersRetrievalResponse =
        ActiveUsersRetrievalResponseBuilder.buildActiveUsersRetrievalResponse(activeUsers);
    assertNotNull(activeUsersRetrievalResponse.getActiveUsers());
  }

  @Test
  public void testActiveUsersRetrievalResponseNotBuilder() {
    List<User>  activeUsers = new ArrayList<>();
    User activeUser = new User.UserBuilder()
        .withId(123)
        .withManagerId(2)
        .withFirstName("Leah")
        .withLastName("Fleming")
        .withEmailAddress("lfleming@hotmail.com")
        .withIsActive(true)
        .build();
    activeUsers.add(activeUser);

    ActiveUsersRetrievalResponse activeUsersRetrievalResponse =
        ActiveUsersRetrievalResponseBuilder.buildActiveUsersRetrievalResponse(activeUsers);

    assertEquals(activeUsersRetrievalResponse.getActiveUsers().size(), 1);
    assertEquals(activeUsersRetrievalResponse.getActiveUsers().get(0)
        .getId(), 123);
    assertEquals(activeUsersRetrievalResponse.getActiveUsers().get(0)
        .getFirstName(), "Leah");
    assertEquals(activeUsersRetrievalResponse.getActiveUsers().get(0)
        .getLastName(), "Fleming");
  }
}
