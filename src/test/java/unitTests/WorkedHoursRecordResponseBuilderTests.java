package unitTests;

import testUtils.TestConstants;
import com.manager.takehome.challenge.builder.WorkedHoursRecordResponseBuilder;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkedHoursRecordResponseBuilderTests {

  @Test
  public void testWorkedHoursRecordResponseBuilderSuccess() {

    WorkedHoursRecordResponse workedHoursRecordResponse = WorkedHoursRecordResponseBuilder
        .buildWorkedHoursRecordResponse(1);
    assertEquals(workedHoursRecordResponse.getPostSuccess(), true);
    assertEquals(workedHoursRecordResponse.getMessage(), TestConstants.POST_HOURS_SUCCESS);
  }

  @Test
  public void testWorkedHoursRecordResponseBuilderFailure() {

    WorkedHoursRecordResponse workedHoursRecordResponse = WorkedHoursRecordResponseBuilder
        .buildWorkedHoursRecordResponse(0);
    assertEquals(workedHoursRecordResponse.getPostSuccess(), false);
    assertEquals(workedHoursRecordResponse.getMessage(), TestConstants.POST_HOURS_FAILED);
  }
}
