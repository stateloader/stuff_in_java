package com.example.greenhouse.repositories;
import com.example.greenhouse.models.Sector;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SectorDAO {

  static final String QUERY_ALL = "select * from sector";

  Properties properties = new Properties();

  public SectorDAO ()   {
    try {
      properties.load(new FileInputStream("src/main/resources/application.properties"));
    } catch (Exception exception){
      exception.printStackTrace();
    }
  }

  public List<Sector> getAllSectors() {
    List<Sector> allSectors = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection(
            properties.getProperty("connectionString"),
            properties.getProperty("spring.datasource.username"),
            properties.getProperty("spring.datasource.password"));
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(QUERY_ALL)) {

      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        allSectors.add(new Sector(id, name));
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return allSectors;
  }
}
