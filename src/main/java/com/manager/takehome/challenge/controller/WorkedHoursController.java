package com.manager.takehome.challenge.controller;

import com.manager.takehome.challenge.adapter.WorkedHoursRecordRequestAdapter;
import com.manager.takehome.challenge.builder.WorkedHoursRecordResponseBuilder;
import com.manager.takehome.challenge.builder.WorkedHoursRetrievalResponseBuilder;
import com.manager.takehome.challenge.dto.v1.Error;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordRequest;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRecordResponse;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRetrievalResponse;
import com.manager.takehome.challenge.service.UserService;
import com.manager.takehome.challenge.service.WorkedHoursService;
import com.manager.takehome.challenge.util.Constants;
import com.manager.takehome.challenge.util.CorrelationIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
public class WorkedHoursController {

  @Autowired
  WorkedHoursService workedHoursService;

  @Autowired
  UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(WorkedHoursController.class);

  /**
   * retrieve all active users in the system.
   * * @return ResponseEntity.
   * @throws Exception when response not correct.
   */
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @RequestMapping(
      value = "/v1/users/{id}/worked_hours",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> retrieveAllWorkedHoursForUser(
      HttpServletRequest httpServletRequest, @PathVariable(required = true) int id)
      throws Exception {
    MDC.clear();
    MDC.put("correlationId", CorrelationIdUtil.getCorrelationIdFromHeader(httpServletRequest));

    if (!userService.checkIfUserExists(id)) {
      WorkedHoursRecordResponse workedHoursRecordResponse = new WorkedHoursRecordResponse();
      workedHoursRecordResponse.setError(new Error()
          .withErrorMessage(Constants.NO_USER)
          .withErrorCode(404));
      return new ResponseEntity<Object>(workedHoursRecordResponse, HttpStatus.NOT_FOUND);
    }

    logger.info("*** Retrieving all hours worked for user" + id);

    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(
            workedHoursService.findAllWorkedHoursByUser(id));

    return new ResponseEntity<Object>(workedHoursRetrievalResponse, HttpStatus.OK);
  }

  /**
   * post worked hours for a user
   * * @return ResponseEntity.
   * @throws Exception when response not correct.
   */
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @RequestMapping(
      value = "/v1/users/{id}/worked_hours",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Object> recordWorkedHours(
      HttpServletRequest httpServletRequest, @PathVariable(required = true) int id,
      @Valid @RequestBody WorkedHoursRecordRequest workedHoursRecordRequest)
      throws Exception {
    MDC.clear();
    MDC.put("correlationId", CorrelationIdUtil.getCorrelationIdFromHeader(httpServletRequest));

    if (!userService.checkIfUserExists(id)) {
      WorkedHoursRecordResponse workedHoursRecordResponse = new WorkedHoursRecordResponse();
      workedHoursRecordResponse.setError(new Error()
          .withErrorMessage(Constants.NO_USER)
          .withErrorCode(404));
      return new ResponseEntity<Object>(workedHoursRecordResponse, HttpStatus.NOT_FOUND);
    }

    if (workedHoursService.checkRecordedHoursForDateByUser(id,
        LocalDate.parse(workedHoursRecordRequest.getDate()))) {
      WorkedHoursRecordResponse workedHoursRecordResponse = new WorkedHoursRecordResponse();
      workedHoursRecordResponse.setError(new Error()
          .withErrorMessage(Constants.HOURS_ALREADY_RECORDED)
          .withErrorCode(412));
      return new ResponseEntity<Object>(workedHoursRecordResponse, HttpStatus.PRECONDITION_FAILED);
    }

    logger.info("*** Recording hours worked for user" + id);
    
    WorkedHoursRecordResponse workedHoursRecordResponse = WorkedHoursRecordResponseBuilder
        .buildWorkedHoursRecordResponse(workedHoursService.saveWorkedHoursByUser(
            id, WorkedHoursRecordRequestAdapter.adaptWorkedHoursRecordRequest(
                id, workedHoursRecordRequest)));

    return new ResponseEntity<Object>(workedHoursRecordResponse, HttpStatus.OK);
  }
}
