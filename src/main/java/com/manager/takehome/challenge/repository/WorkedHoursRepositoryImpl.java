package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.WorkedHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkedHoursRepositoryImpl implements WorkedHoursRepository {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /**
   * saves worked hours for a user.
   * * @return affected rows
   */
  public int saveWorkedHoursByUser(int id, WorkedHours workedHours) {
    return namedParameterJdbcTemplate.update("insert into worked_hours "
        + "(user_id, date, hours) values (:userId,  :date, :hours);",
        new BeanPropertySqlParameterSource(workedHours));
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
                rs.getDate("date").toLocalDate(),
                rs.getBigDecimal("hours")
            ).build()
    );
  }
}
