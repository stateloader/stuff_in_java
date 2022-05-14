package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {

  private int tableLength;

  public SensorRepository() {}

  public int getTableLength() {return tableLength;}
  public void setTableLength(int tableLength) {this.tableLength = tableLength;}

  public List<SensorData> getTemperatureReadingBySector(int sectorID) {

    // queries all temperature-data from a given sector.

    Connect connect = new Connect();
    Queries queries = new Queries();
    List<SensorData> sensorData;

    Connection connection = connect.loadConnection();
    sensorData = queries.getSectorTemperature(connection, sectorID);
    setTableLength(sensorData.size());

    return sensorData;
  }

  public List<SensorData> getHumidityReadingBySector(int sectorID) {

    // queries all humidity-data from a given sector.

    Connect connect = new Connect();
    Queries queries = new Queries();
    List<SensorData> sensorData;

    Connection connection = connect.loadConnection();
    sensorData = queries.getSectorByHumidity(connection, sectorID);
    return sensorData;
  }

  public float getAverageTemperature(int sectorID) {

    Calculator calculator = new Calculator();
    List<Float> temperatureValues = new ArrayList<>();

    List<SensorData> temperatureTable= getTemperatureReadingBySector(sectorID);

    for (SensorData sensorData : temperatureTable)
      temperatureValues.add(sensorData.getData());

    return calculator.calculateAverage(temperatureValues);
  }

  public float getAverageHumidity(int sectorID) {

    Calculator calculator = new Calculator();
    List<Float> humidityValues = new ArrayList<>();

    List<SensorData> humidityTable = getHumidityReadingBySector(sectorID);

    for (SensorData sensorData : humidityTable)
      humidityValues.add(sensorData.getData());

    return calculator.calculateAverage(humidityValues);
  }

  public String getSectorName(int sectorID) {

    // ugly solution for now.

    switch(sectorID) {
      case 1: return "SECTOR A";
      case 2: return "SECTOR B";
      case 3: return "SECTOR C";
      default: return "NO SECTOR";
    }
  }
}