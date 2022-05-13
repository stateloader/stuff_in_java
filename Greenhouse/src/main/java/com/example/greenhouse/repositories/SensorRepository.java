package com.example.greenhouse.repositories;

import com.example.greenhouse.models.Sensor;
import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {

  public List<SensorData> getAllSensorData() {

    Connect connect = new Connect();
    List<SensorData> sensorDataTable = new ArrayList<>();

    try {
      Connection connection = connect.loadConnection();
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(Queries.ALL_SENSOR_DATA);

      while (resultset.next()) {
        int id = resultset.getInt("id");
        float data = resultset.getFloat("data");
        Timestamp timestamp = resultset.getTimestamp("timestamp");
        int sector_id = resultset.getInt("sector_id");
        int sensor_id = resultset.getInt("sensor_id");
        sensorDataTable.add(new SensorData(id, data, timestamp, sector_id, sensor_id));
      }

      return sensorDataTable;

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }
}