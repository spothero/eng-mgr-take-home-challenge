package com.manager.takehome.challenge.models;

import java.sql.Date;
import java.sql.Timestamp;

public class WorkedHours
{
  private long userId;
  private Date date;
  private float hours;
  private Timestamp createdAtTimeStamp;

  private WorkedHours(WorkedHoursBuilder builder) {
    this.userId = builder.userId;
    this.date = builder.date;
    this.hours = builder.hours;
    this.createdAtTimeStamp = builder.createdAtTimeStamp;
  }

  public long getUserId() {
    return userId;
  }

  public Date getDate() {
    return date;
  }

  public float getHours() {
    return hours;
  }

  public Timestamp getCreatedAtTimeStamp() {
    return createdAtTimeStamp;
  }

  @Override
  public String toString() {
    return "User: "+this.userId+", "+this.date+", "+this.hours+", "+this.createdAtTimeStamp;
  }

  public static class WorkedHoursBuilder
  {
    private long userId;
    private Date date;
    private float hours;
    private Timestamp createdAtTimeStamp;

    public WorkedHoursBuilder() {
    }

    public WorkedHoursBuilder(long userId, Date date, float hours) {
      this.userId = userId;
      this.date = date;
      this.hours = hours;
    }

    public WorkedHoursBuilder withUserId(long userId) {
      this.userId = userId;
      return this;
    }

    public WorkedHoursBuilder withDate(Date date) {
      this.date = date;
      return this;
    }

    public WorkedHoursBuilder withHours(float hours) {
      this.hours = hours;
      return this;
    }

    public WorkedHoursBuilder withCreatedAtTimeStamp(Timestamp createdAtTimeStamp) {
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
