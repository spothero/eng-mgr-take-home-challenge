package com.manager.takehome.challenge.builder;

import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordResponse;
import com.manager.takehome.challenge.util.Constants;

public class WorkedHoursRecordResponseBuilder {

  /**
  * builds WorkedHoursRecordResponse DTO.
  */
  public  static WorkedHoursRecordResponse buildWorkedHoursRecordResponse(int rowsAffected) {

    WorkedHoursRecordResponse workedHoursRecordResponse = new WorkedHoursRecordResponse();
    if (rowsAffected > 0) {
      workedHoursRecordResponse.setPostSuccess(true);
      workedHoursRecordResponse.setMessage(Constants.POST_HOURS_SUCCESS);
    }
    return workedHoursRecordResponse;
  }
}
