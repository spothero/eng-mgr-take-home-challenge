package com.manager.takehome.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class AppConfigurations {

  @Value("${server.port}")
  private String serverPort;

  @Value("${management.server.port}")
  private String managementServerPort;

  @Value("${management.server.address}")
  private String managementServerAddress;

  public String getServerPort() {
    return serverPort;
  }

  public String getManagementServerPort() {
    return managementServerPort;
  }

  public String getManagementServerAddress() {
    return managementServerAddress;
  }
}
