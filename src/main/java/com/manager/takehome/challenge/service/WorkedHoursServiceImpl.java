package com.manager.takehome.challenge.service;

import com.manager.takehome.challenge.models.WorkedHours;
import com.manager.takehome.challenge.repository.WorkedHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkedHoursServiceImpl implements WorkedHoursService {
  @Autowired
  WorkedHoursRepository workedHoursRepository;

  @Override
  public int saveWorkedHoursByUser(int id, WorkedHours workedHours) {
    return workedHoursRepository.saveWorkedHoursByUser(id, workedHours);
  }

  @Override
  public List<WorkedHours> findAllWorkedHoursByUser(int userId) {
    return workedHoursRepository.findAllWorkedHoursByUser(userId);
  }

  @Override
  public boolean checkRecordedHoursForDateByUser(int userId, LocalDate date) {
    return workedHoursRepository.checkRecordedHoursForDateByUser(userId, date);

  }
}
