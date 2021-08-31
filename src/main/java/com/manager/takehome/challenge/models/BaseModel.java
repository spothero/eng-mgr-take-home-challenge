package com.manager.takehome.challenge.models;

public class BaseModel {

  private final long id;
  private final String content;

  public BaseModel(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
