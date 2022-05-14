package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.List;

public class SensorRepository {

  public SensorRepository() {}

  public List<SensorData> getAllSensorData(int sectorID) {

    Connect connect = new Connect();
    Queries queries = new Queries();
    List<SensorData> sensorData;

    Connection connection = connect.loadConnection();
    sensorData = queries.getSectorTemperature(connection, sectorID);
    return sensorData;
  }
}