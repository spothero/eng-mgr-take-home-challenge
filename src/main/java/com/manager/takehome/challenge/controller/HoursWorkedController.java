package com.manager.takehome.challenge.controller;

import com.manager.takehome.challenge.builder.WorkedHoursRetrievalResponseBuilder;
import com.manager.takehome.challenge.dto.v1.WorkedHoursRetrievalResponse;
import com.manager.takehome.challenge.service.WorkedHoursService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HoursWorkedController {

  @Autowired
  WorkedHoursService workedHoursService;

  private static final Logger logger = LoggerFactory.getLogger(HoursWorkedController.class);

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
    logger.info("*** Retrieving all hours worked for user" + id);

    WorkedHoursRetrievalResponse workedHoursRetrievalResponse =
        WorkedHoursRetrievalResponseBuilder.buildWorkedHoursRetrievalResponse(
            workedHoursService.findAllWorkedHoursByUser(id));

    return new ResponseEntity<Object>(workedHoursRetrievalResponse, HttpStatus.OK);
  }
}
