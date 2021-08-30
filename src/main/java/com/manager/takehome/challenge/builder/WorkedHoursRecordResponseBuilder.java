package com.manager.takehome.challenge.builder;

import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordResponse;

public class WorkedHoursRecordResponseBuilder {

  /**
  * builds an WorkedHoursRecordResponse DTO.
  */
  public  static WorkedHoursRecordResponse buildWorkedHoursRecordResponse(int rowsAffected) {

    WorkedHoursRecordResponse workedHoursRecordResponse = new WorkedHoursRecordResponse();
    if (rowsAffected > 0) {
      workedHoursRecordResponse.setPostageSuccess(true);
      workedHoursRecordResponse.setMessage("posted successfully");
    }
    return workedHoursRecordResponse;
  }
}
