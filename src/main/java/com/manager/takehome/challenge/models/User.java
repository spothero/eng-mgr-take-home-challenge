package com.manager.takehome.challenge.models;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public class User
{
  private long id;
  private long managerId;
  private String firstName;
  private String lastName;
  private String email;
  private boolean isActive;
  private Timestamp createdAtTimeStamp;

  private User(UserBuilder builder) {
    this.id = builder.id;
    this.managerId = builder.managerId;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.isActive = builder.isActive;
    this.createdAtTimeStamp = builder.createdAtTimeStamp;
  }

  public long getUserId() {
    return id;
  }

  public long getManagerId() {
    return managerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() {
    return email;
  }

  public boolean isActive() {
    return isActive;
  }

  public Timestamp getCreatedAtTimeStamp() {
    return createdAtTimeStamp;
  }

  @Override
  public String toString() {
    return "User: "+this.id+", "+this.managerId+", "+this.firstName+", "+this.lastName+", "+this.email+", "+this.isActive+", "+this.createdAtTimeStamp;
  }

  public static class UserBuilder
  {
    @Id
    private long id;
    private long managerId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private Timestamp createdAtTimeStamp;

    public UserBuilder() {
    }

    public UserBuilder(long id, String firstName, String lastName) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
    }

    public UserBuilder withId(long id) {
      this.id = id;
      return this;
    }

    public UserBuilder withManagerId(long managerId) {
      this.managerId = managerId;
      return this;
    }

    public UserBuilder withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public UserBuilder withLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public UserBuilder withEmailAddress(String email) {
      this.email = email;
      return this;
    }

    public UserBuilder withIsActive(boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    public UserBuilder withCreatedAtTimeStamp(Timestamp createdAtTimeStamp) {
      this.createdAtTimeStamp = createdAtTimeStamp;
      return this;
    }
    //Return the finally constructed User object
    public User build() {
      User user =  new User(this);
      return user;
    }
  }
}
