package com.manager.takehome.challenge.controller;

import com.manager.takehome.challenge.config.AppConfigurations;
import com.manager.takehome.challenge.models.BaseModel;

import com.manager.takehome.challenge.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BaseController {

  @Autowired private AppConfigurations appConfigurations;
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public BaseModel sayHello() {
    return new BaseModel(counter.incrementAndGet(), Constants.SERVICE_UP);
  }

  @RequestMapping(value = "/version", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String version() {
    final String version = getClass().getPackage().getSpecificationVersion();
    return version == null ? Constants.DEV : version;
  }

  @RequestMapping(value = "/version-details", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String versionDetails() {
    final String version = getClass().getPackage().getImplementationVersion();
    return version == null ? Constants.DEV : version;
  }

  /**
   *
   * @return app configuration.
   */
  @RequestMapping(value = "/appconfig", produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String getAppConfigurations() {

    return ", server port:"
        + appConfigurations.getServerPort()
        + ", management port:"
        + appConfigurations.getManagementServerPort()
        + ", management server address:"
        + appConfigurations.getManagementServerAddress();
  }
}
