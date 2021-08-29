package com.manager.takehome.challenge.controller;

import com.manager.takehome.challenge.builder.ActiveUsersRetrievalResponseBuilder;
import com.manager.takehome.challenge.dto.v1.ActiveUsersRetrievalResponse;
import com.manager.takehome.challenge.service.UserService;
import com.manager.takehome.challenge.util.CorrelationIdUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RetrieveUsersController {

  @Autowired
  UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(RetrieveUsersController.class);

  /**
   * retrieve all active users in the system.
   * * @return ResponseEntity.
   * @throws Exception when response not correct.
   */
  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @RequestMapping(
      value = "/v1/users",
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Object> retrieveAllActiveUsers(HttpServletRequest httpServletRequest)
      throws Exception {
    logger.info("*** Retrieving all active users***");
    MDC.clear();
    MDC.put("correlationId", CorrelationIdUtil.getCorrelationIdFromHeader(httpServletRequest));

    ActiveUsersRetrievalResponse activeUsersRetrievalResponse =
        ActiveUsersRetrievalResponseBuilder.buildActiveUsersRetrievalResponse(
            userService.findAllActiveUsers());

    return new ResponseEntity<Object>(activeUsersRetrievalResponse, HttpStatus.OK);
  }

}
