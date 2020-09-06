package io.mbab.sda.groupproject.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import io.mbab.sda.groupproject.entity.Albums;
import io.mbab.sda.groupproject.entity.Songs;

import javax.sql.DataSource;

public final class Configuration {
  private static final String DB_URL =
      "jdbc:mysql://localhost:3306/cd_library?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&allowPublicKeyRetrieval=true";
  private static final String DB_USER_NAME = "root";
  private static final String DB_PASSWORD = "MiBu92";

  private static DataSource dataSource;

  public static DataSource getDataSource() {
    if (dataSource != null) return dataSource;

    var ds = new MysqlDataSource();
    ds.setURL(DB_URL);
    ds.setUser(DB_USER_NAME);
    ds.setPassword(DB_PASSWORD);

    dataSource = ds;

    return dataSource;
  }

  /* W tej metodzie należy zwrócić liste klas które są encjami, aplikacja samodzielnie ich nie zmapuje */
  public static Class[] getEntityClass() {
    return new Class[] {Albums.class , Songs.class};
  }
}
