package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.WorkedHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkedHoursRepositoryImpl implements WorkedHoursRepository {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public int saveWorkedHoursByUser(WorkedHours workedHours) {

    return 1;
  }

  /**
  * retrieve all worked hours for a user.
  * * @return List of workedHours
  */
  public List<WorkedHours> findAllWorkedHoursByUser(long userId) {

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id",  userId );

    return namedParameterJdbcTemplate.query("select worked_hours.user_id, "
            + "worked_hours.date, worked_hours.hours from worked_hours "
            + "where worked_hours.user_id = :id",
        mapSqlParameterSource,
        (rs, rowNum) ->
            new WorkedHours.WorkedHoursBuilder(
                rs.getInt("user_id"),
                rs.getDate("date"),
                rs.getBigDecimal("hours")
            ).build()
    );
  }
}
