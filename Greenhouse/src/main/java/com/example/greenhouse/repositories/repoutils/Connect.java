package com.example.greenhouse.repositories.repoutils;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class Connect {
//Klass inom vilken all SQL-connectivity sköts.

  // "Get-property-strängar" från application.properties-filen (som ej pushats till GIT).
  private static final String STR_PROPERTY = "src/main/resources/application.properties";
  private static final String STR_CONNECT = "connectionString";
  private static final String STR_USERNAME = "spring.datasource.username";
  private static final String STR_PASSWORD = "spring.datasource.password";

  public Connect() {}

  public Connection connectDatabase() {
    // Kontaktar (eller försöker - ingen felhantering på plats) databasen.

    Properties properties = new Properties();
    Connection connection;

    try {
      properties.load(new FileInputStream(STR_PROPERTY));
      connection = DriverManager.getConnection(
              properties.getProperty(STR_CONNECT),
              properties.getProperty(STR_USERNAME),
              properties.getProperty(STR_PASSWORD)
      );
      return connection;
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }
  }
}