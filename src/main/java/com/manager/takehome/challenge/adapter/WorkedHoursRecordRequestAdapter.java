package com.manager.takehome.challenge.adapter;

import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordRequest;
import com.manager.takehome.challenge.models.WorkedHours;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkedHoursRecordRequestAdapter {

  /**
  * adapts a WorkedHours DTO to the WorkedHours internal model.
  */
  public static WorkedHours adaptWorkedHoursRecordRequest(
      int id, WorkedHoursRecordRequest workedHoursRecordRequest) {
    WorkedHours workedHours = new WorkedHours.WorkedHoursBuilder()
        .withUserId(id)
        .withDate(LocalDate.parse(workedHoursRecordRequest.getDate()))
        .withHours(BigDecimal.valueOf(workedHoursRecordRequest.getHours()))
            .build();
    return workedHours;
  }
}
