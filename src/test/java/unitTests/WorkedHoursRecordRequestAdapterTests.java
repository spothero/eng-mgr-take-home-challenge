package unitTests;

import com.manager.takehome.challenge.adapter.WorkedHoursRecordRequestAdapter;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordRequest;
import com.manager.takehome.challenge.models.WorkedHours;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkedHoursRecordRequestAdapterTests {


  @Test
  public void testWorkedHoursRecordRequestAdapter() {

    WorkedHoursRecordRequest workedHoursRecordRequest = new WorkedHoursRecordRequest()
        .withDate("2021-01-08")
        .withHours(5.5);

    WorkedHours workedHours = WorkedHoursRecordRequestAdapter.adaptWorkedHoursRecordRequest(1,
        workedHoursRecordRequest);

    assertEquals(workedHours.getDate(), LocalDate.of(2021, 1, 8));
    assertEquals(workedHours.getHours(), BigDecimal.valueOf(5.5));
    assertEquals(workedHours.getUserId(), 1);
  }
}
