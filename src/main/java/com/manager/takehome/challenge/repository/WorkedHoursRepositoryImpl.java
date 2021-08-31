package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.WorkedHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    return namedParameterJdbcTemplate.update(PostgreSqlUtil.SAVE_WORKED_HOURS_FOR_USER_QUERY,
        new BeanPropertySqlParameterSource(workedHours));
  }

  /**
  * retrieve all worked hours for a user.
  * * @return List of workedHours
  */
  public List<WorkedHours> findAllWorkedHoursByUser(int userId) {

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id",  userId);

    return namedParameterJdbcTemplate.query(PostgreSqlUtil.FIND_ALL_WORKED_HOURS_BY_USER_QUERY,
        mapSqlParameterSource,
        (rs, rowNum) ->
            new WorkedHours.WorkedHoursBuilder(
                rs.getInt("user_id"),
                rs.getDate("date").toLocalDate(),
                rs.getBigDecimal("hours")
            ).build()
    );
  }

  @Override
  public boolean checkRecordedHoursForDateByUser(int userId, LocalDate date) {

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("id", userId);
    mapSqlParameterSource.addValue("date", date);

    return namedParameterJdbcTemplate.queryForObject(
        PostgreSqlUtil.CHECK_RECORDED_HOURS_FOR_DATE_BY_USER_QUERY,
        mapSqlParameterSource, Boolean.class
    );
  }
}
