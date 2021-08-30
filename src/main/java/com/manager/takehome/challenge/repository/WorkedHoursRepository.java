package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.WorkedHours;

import java.util.List;

public interface WorkedHoursRepository {

  int saveWorkedHoursByUser(int id, WorkedHours workedHours);

  List<WorkedHours> findAllWorkedHoursByUser(long userId);
}
