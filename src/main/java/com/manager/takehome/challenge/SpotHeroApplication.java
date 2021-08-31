package com.manager.takehome.challenge;

import com.manager.takehome.challenge.config.AppConfigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpotHeroApplication {

  @Autowired
  private AppConfigurations appConfig;

  public static void main(String[] args) {
    SpringApplication.run(SpotHeroApplication.class);
  }

  /**
   * method to display app configs.
   */
  public void run(String... args) throws Exception {
    System.out.println("using server port:" + appConfig.getServerPort());
    System.out.println("using management port:" + appConfig.getManagementServerPort());
    System.out.println("using management server address:" + appConfig.getManagementServerAddress());
  }
}

