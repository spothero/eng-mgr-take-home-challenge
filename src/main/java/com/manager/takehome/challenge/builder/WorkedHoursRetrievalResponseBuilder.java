package com.manager.takehome.challenge.builder;

import com.manager.takehome.challenge.dto.v1.WorkedHoursRetrievalResponse;
import com.manager.takehome.challenge.models.WorkedHours;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkedHoursRetrievalResponseBuilder {

  /**
  * builds an WorkedHoursRetrievalResponse DTO.
  */
  public static WorkedHoursRetrievalResponse buildWorkedHoursRetrievalResponse(
      List<WorkedHours> listOfWorkedHours) {
    WorkedHoursRetrievalResponse workedHoursRetrievalResponse = new WorkedHoursRetrievalResponse();
    List<com.manager.takehome.challenge.dto.v1.WorkedHours> workedHoursDtoList = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(listOfWorkedHours)) {
      for (WorkedHours workedHours : listOfWorkedHours) {
        com.manager.takehome.challenge.dto.v1.WorkedHours workedHoursDto =
            new com.manager.takehome.challenge.dto.v1.WorkedHours();
        workedHoursDto.setId(workedHours.getUserId());
        workedHoursDto.setDate(workedHours.getDate().toString());
        workedHoursDto.setHours(workedHours.getHours().doubleValue());
        workedHoursDtoList.add(workedHoursDto);
      }
      workedHoursRetrievalResponse.setWorkedHours(workedHoursDtoList);

    }
    return workedHoursRetrievalResponse;
  }
}
