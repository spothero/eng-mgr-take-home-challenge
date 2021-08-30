package com.manager.takehome.challenge.service;

import com.manager.takehome.challenge.models.WorkedHours;

import java.util.List;

public interface WorkedHoursService {

  int  saveWorkedHoursByUser(int id, WorkedHours workedHours);

  List<WorkedHours> findAllWorkedHoursByUser(long userId);
}
