package com.manager.takehome.challenge.service;

import com.manager.takehome.challenge.models.WorkedHours;
import com.manager.takehome.challenge.repository.WorkedHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkedHoursServiceImpl implements WorkedHoursService {
  @Autowired
  WorkedHoursRepository workedHoursRepository;

  @Override
  public int saveWorkedHoursByUser(WorkedHours workedHours) {
    return workedHoursRepository.saveWorkedHoursByUser(workedHours);
  }

  @Override
  public List<WorkedHours> findAllWorkedHoursByUser(long userId) {
    return workedHoursRepository.findAllWorkedHoursByUser(userId);

  }
}
