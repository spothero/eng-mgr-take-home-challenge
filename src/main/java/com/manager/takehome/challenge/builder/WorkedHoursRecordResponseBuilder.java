package com.manager.takehome.challenge.builder;

import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordResponse;
import com.manager.takehome.challenge.util.Constants;

public class WorkedHoursRecordResponseBuilder {

  /**
  * builds WorkedHoursRecordResponse DTO.
  */
  public  static WorkedHoursRecordResponse buildWorkedHoursRecordResponse(int rowsAffected) {

    WorkedHoursRecordResponse workedHoursRecordResponse = new WorkedHoursRecordResponse();
    if (rowsAffected == 1) {
      workedHoursRecordResponse.setPostSuccess(true);
      workedHoursRecordResponse.setMessage(Constants.POST_HOURS_SUCCESS);
    } else {
      workedHoursRecordResponse.setPostSuccess(false);
      workedHoursRecordResponse.setMessage(Constants.POST_HOURS_FAILED);
    }
    return workedHoursRecordResponse;
  }
}
