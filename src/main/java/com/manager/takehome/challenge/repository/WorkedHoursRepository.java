package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.WorkedHours;

import java.time.LocalDate;
import java.util.List;

public interface WorkedHoursRepository {

  int saveWorkedHoursByUser(int id, WorkedHours workedHours);

  List<WorkedHours> findAllWorkedHoursByUser(int userId);

  boolean checkRecordedHoursForDateByUser(int userId, LocalDate date);

}
