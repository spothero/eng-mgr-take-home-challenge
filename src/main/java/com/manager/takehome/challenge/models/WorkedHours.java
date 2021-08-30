package com.manager.takehome.challenge.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkedHours
{
  private int userId;
  private LocalDate date;
  private BigDecimal hours;
  private LocalDateTime createdAtTimeStamp;

  private WorkedHours(WorkedHoursBuilder builder) {
    this.userId = builder.userId;
    this.date = builder.date;
    this.hours = builder.hours;
    this.createdAtTimeStamp = builder.createdAtTimeStamp;
  }

  public int getUserId() {
    return userId;
  }

  public LocalDate getDate() {
    return date;
  }

  public BigDecimal getHours() {
    return hours;
  }

  public LocalDateTime getCreatedAtTimeStamp() {
    return createdAtTimeStamp;
  }

  @Override
  public String toString() {
    return "User: "+this.userId+", "+this.date+", "+this.hours+", "+this.createdAtTimeStamp;
  }

  public static class WorkedHoursBuilder
  {
    private int userId;
    private LocalDate date;
    private BigDecimal hours;
    private LocalDateTime createdAtTimeStamp;

    public WorkedHoursBuilder() {
    }

    public WorkedHoursBuilder(int userId, LocalDate date, BigDecimal hours) {
      this.userId = userId;
      this.date = date;
      this.hours = hours;
    }

    public WorkedHoursBuilder withUserId(int userId) {
      this.userId = userId;
      return this;
    }

    public WorkedHoursBuilder withDate(LocalDate date) {
      this.date = date;
      return this;
    }

    public WorkedHoursBuilder withHours(BigDecimal hours) {
      this.hours = hours;
      return this;
    }

    public WorkedHoursBuilder withCreatedAtTimeStamp(LocalDateTime createdAtTimeStamp) {
      this.createdAtTimeStamp = createdAtTimeStamp;
      return this;
    }
    //Return the finally constructed WorkedHours object
    public WorkedHours build() {
      WorkedHours workedHours =  new WorkedHours(this);
      return workedHours;
    }
  }
}
