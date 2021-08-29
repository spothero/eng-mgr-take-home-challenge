package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.WorkedHours;

import java.util.List;

public interface WorkedHoursRepository {

  int saveWorkedHoursByUser(WorkedHours workedHours);

  List<WorkedHours> findAllWorkedHoursByUser(long userId);
}
