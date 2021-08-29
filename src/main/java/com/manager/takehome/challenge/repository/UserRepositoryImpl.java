package com.manager.takehome.challenge.repository;

import com.manager.takehome.challenge.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Override
  public List<User> findAllActiveUser() {

    MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    mapSqlParameterSource.addValue("isActive", true);

    return namedParameterJdbcTemplate.query("select users.id, "
            + "users.first_name, users.last_name, users.email from users"
            + " where users.active = :isActive",
        mapSqlParameterSource,
        (rs, rowNum) ->
            new User.UserBuilder(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name")
            ).withEmailAddress(rs.getString("email"))
                .build()
    );
  }
}
