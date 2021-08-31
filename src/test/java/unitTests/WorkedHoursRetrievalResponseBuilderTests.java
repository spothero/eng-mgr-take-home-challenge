package unitTests;

import com.manager.takehome.challenge.builder.WorkedHoursRetrievalResponseBuilder;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRetrievalResponse;
import com.manager.takehome.challenge.models.WorkedHours;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WorkedHoursRetrievalResponseBuilderTests {


  @Test
  public void testWorkedHoursRetrievalResponseNotNull() {
    List<WorkedHours> workedHours = null;
    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(workedHours);
    assertNotNull(workedHoursRetrievalResponse);
  }

  @Test
  public void testListOfWorkedHoursNotNull() {
    List<WorkedHours> workedHours = null;
    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(workedHours);
    assertNotNull(workedHoursRetrievalResponse.getWorkedHours());
  }

  @Test
  public void testWorkedHoursRetrievalResponseNotNullWhenNoHoursWorked() {
    List<WorkedHours>  listOfWorkedHours = new ArrayList<>();
    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(listOfWorkedHours);
    assertNotNull(workedHoursRetrievalResponse);
  }


  @Test
  public void testListOfWorkedHoursNotNullWhenNoHoursWorked() {
    List<WorkedHours>  listOfWorkedHours = new ArrayList<>();
    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(listOfWorkedHours);
    assertNotNull(workedHoursRetrievalResponse.getWorkedHours());
  }

  @Test
  public void testWorkedHoursRetrievalResponseBuilder() {
    List<WorkedHours>  listOfWorkedHours = new ArrayList<>();
    WorkedHours workedHours =new WorkedHours.WorkedHoursBuilder()
        .withUserId(123)
        .withHours(BigDecimal.valueOf(5.5))
        .withDate(LocalDate.of(2020, 1, 8))
        .build();

    listOfWorkedHours.add(workedHours);

    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(listOfWorkedHours);

    assertEquals(workedHoursRetrievalResponse.getWorkedHours().size(), 1);
    assertEquals(workedHoursRetrievalResponse.getWorkedHours().get(0)
        .getId(), 123);
    assertEquals(workedHoursRetrievalResponse.getWorkedHours().get(0)
        .getHours(), 5.5);
    assertEquals(workedHoursRetrievalResponse.getWorkedHours().get(0)
        .getDate(), "2020-01-08");
  }
}
