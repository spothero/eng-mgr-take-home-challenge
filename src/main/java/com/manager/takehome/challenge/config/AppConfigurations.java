package com.manager.takehome.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class AppConfigurations {

  @Value("${spring.profiles}")
  private String environment;

  @Value("${server.port}")
  private String serverPort;

  @Value("${server.servlet.contextPath}")
  private String contextPath;

  @Value("${management.server.port}")
  private String managementServerPort;

  @Value("${management.server.address}")
  private String managementServerAddress;

  @Value("${spring.profiles}")
  private String springProfile;

  public String getEnvironment() {
    return environment;
  }

  public String getServerPort() {
    return serverPort;
  }

  public String getContextPath() {
    return contextPath;
  }

  public String getManagementServerPort() {
    return managementServerPort;
  }

  public String getManagementServerAddress() {
    return managementServerAddress;
  }

  public String getSpringProfile() {
    return springProfile;
  }

}
