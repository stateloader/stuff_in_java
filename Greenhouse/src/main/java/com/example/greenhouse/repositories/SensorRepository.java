package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {

  private int sizeTemperatureData;
  private int sizeHumidityData;

  public SensorRepository() {}

  // Getters, size of various lists storing various data. Used when fetching latest "reading" [see SensorController].
  public int getSizeTemperatureData() {return sizeTemperatureData;}
  public int getSizeHumidityData() {return sizeHumidityData;}

  // Setters, sets size of various lists storing various data.
  public void setSizeTemperatureData(int sizeTemperatureData) {this.sizeTemperatureData = sizeTemperatureData;}
  public void setSizeHumidityData(int sizeHumidityData) {this.sizeHumidityData = sizeHumidityData;}

  public List<SensorData> getTemperatureReadingBySector(int sectorID) {

    // Queries and returns all temperature-data from a given sector [see class Query].

    Connect connect = new Connect();
    Queries queries = new Queries();
    List<SensorData> temperatureTable;

    Connection connection = connect.loadConnection();
    temperatureTable = queries.getTemperatureBySector(connection, sectorID);
    setSizeTemperatureData(temperatureTable.size());

    return temperatureTable;
  }

  public List<SensorData> getHumidityReadingBySector(int sectorID) {

    // Queries and returns all humidity-data from a given sector [see class Query].

    Connect connect = new Connect();
    Queries queries = new Queries();
    List<SensorData> humidityTable;

    Connection connection = connect.loadConnection();
    humidityTable = queries.getHumidityBySector(connection, sectorID);
    setSizeHumidityData(humidityTable.size());

    return humidityTable;
  }

  public float getAverageTemperature(int sectorID) {

    // Queries given sector's temperature-data, calculates average and returns it [see class Query, Calculator].

    Calculator calculator = new Calculator();
    List<Float> temperatureValues = new ArrayList<>();

    List<SensorData> temperatureTable = getTemperatureReadingBySector(sectorID);

    for (SensorData sensorData : temperatureTable)
      temperatureValues.add(sensorData.getData());

    return calculator.calculateAverage(temperatureValues);
  }

  public float getAverageHumidity(int sectorID) {

    // Queries given sector's humidity-data, calculates average and returns it [see class Query, Calculator].

    Calculator calculator = new Calculator();
    List<Float> humidityValues = new ArrayList<>();

    List<SensorData> humidityTable = getHumidityReadingBySector(sectorID);

    for (SensorData sensorData : humidityTable)
      humidityValues.add(sensorData.getData());

    return calculator.calculateAverage(humidityValues);
  }

  public String getSectorName(int sectorID) {

    // Ugly solution for now.

    switch(sectorID) {
      case 1: return "SECTOR A";
      case 2: return "SECTOR B";
      case 3: return "SECTOR C";
      default: return "NO SECTOR";
    }
  }
}