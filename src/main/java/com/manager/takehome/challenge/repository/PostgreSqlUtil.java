package com.manager.takehome.challenge.repository;

public class PostgreSqlUtil {

  public static final String SAVE_WORKED_HOURS_FOR_USER_QUERY = "insert into worked_hours "
      + "(user_id, date, hours) values (:userId,  :date, :hours)";

  public static final String FIND_ALL_WORKED_HOURS_BY_USER_QUERY = "select worked_hours.user_id, "
      + "worked_hours.date, worked_hours.hours from worked_hours where worked_hours.user_id = :id";

  public static final String FIND_ALL_ACTIVE_USERS_QUERY = "select users.id, users.first_name, "
      + "users.last_name, users.email from users where users.active = :isActive";

  public static final String CHECK_USER_QUERY = "select exists(select 1 from users "
      + "where users.id = :id)";

  public static final String CHECK_RECORDED_HOURS_FOR_DATE_BY_USER_QUERY = "select exists(select 1 "
      + "from worked_hours where worked_hours.user_id = :id and date = :date)";
}
