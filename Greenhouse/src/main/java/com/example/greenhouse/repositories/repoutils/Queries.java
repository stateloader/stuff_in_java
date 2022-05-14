package com.example.greenhouse.repositories.repoutils;

import com.example.greenhouse.models.modelutils.SensorData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {

  public static final String ALL_SECTORS = "SELECT * FROM sector";
  public static final String ALL_ENERGY_DATA = "SELECT * FROM energy_data";
  public static final String ADD_ENERGY_DATA = "INSERT INTO energy_data (price, timestamp) VALUES (?, NOW())";
  public static final String SENSOR_TEMP = "SELECT * FROM sensor WHERE sensor_id = 1";
  public static final String SENSOR_HUMID = "SELECT * FROM sensor WHERE sensor_id = 2";
  public static final String ALL_SENSOR_DATA = "SELECT * FROM sensor_data";
  public static final String ALL_SENSOR_DATA_BY_SECTOR = "SELECT * FROM sensor_data where sector_id = ?";
  public static final String TEMP_BY_SECTOR = "SELECT * FROM sensor_data WHERE sensor_id = 1 AND sector_id = ?";
  public static final String ALL_HUMID_BY_SECTOR = "SELECT * FROM sensor_data WHERE sensor_id = 2 AND sector_id = ?";

  public Queries() {}

  public List<SensorData> getSectorTemperature(Connection connection, int sectorID) {

    List<SensorData> sensorData= new ArrayList<>();

    try {
      PreparedStatement statement = connection.prepareStatement(TEMP_BY_SECTOR);
      statement.setInt(1, sectorID);
      ResultSet resultset = statement.executeQuery();

      while (resultset.next()) {
        int id = resultset.getInt("id");
        float data = resultset.getFloat("data");
        Timestamp timestamp = resultset.getTimestamp("timestamp");
        int sector_id = resultset.getInt("sector_id");
        int sensor_id = resultset.getInt("sensor_id");
        sensorData.add(new SensorData(id, data, timestamp, sector_id, sensor_id));
      }
      return sensorData;
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }
}
