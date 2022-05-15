package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {

  private int sizeTemperatureTable;
  private int sizeHumidityTable;

  private float averageTemperature;
  private float averageHumidity;

  private List<SensorData> temperatureTable;
  private List<SensorData> humidityTable;

  private SensorData lastTemperatureData;
  private SensorData lastHumidityData;

  public SensorRepository() {}

  // Getters, size of various lists storing various data. Used when fetching latest "reading" [see SensorController].
  public int getSizeTemperatureTable() {return sizeTemperatureTable;}
  public int getSizeHumidityTable() {return sizeHumidityTable;}
  public float getAverageTemperature() {return averageTemperature;}
  public float getAverageHumidity() {return averageHumidity;}

  public List<SensorData> getTemperatureTable() {return temperatureTable;}
  public List<SensorData> getHumidityTable() {return humidityTable;}

  public SensorData getLastTemperatureData() {return lastTemperatureData;}
  public SensorData getLastHumidityData() {return lastHumidityData;}

  // Setters, sets size of various lists storing various data.
  public void setSizeTemperatureTable(int sizeTemperatureTable) {this.sizeTemperatureTable = sizeTemperatureTable;}
  public void setSizeHumidityTable(int sizeHumidityTable) {this.sizeHumidityTable = sizeHumidityTable;}

  public void setAverageTemperature(float averageTemperature) {this.averageTemperature = averageTemperature;}
  public void setAverageHumidity(float averageHumidity) {this.averageHumidity = averageHumidity;}

  public void setTemperatureTable(List<SensorData> temperatureTable) {this.temperatureTable = temperatureTable;}
  public void setHumidityTable(List<SensorData> humidityTable) {this.humidityTable = humidityTable;}

  public void setLastTemperatureData(SensorData lastTemperatureData) {this.lastTemperatureData = lastTemperatureData;}
  public void setLastHumidityData(SensorData lastHumidityData) {this.lastHumidityData = lastHumidityData;}

  public void fetchTemperatureReadingBySector(int sectorID) {

    Connect connect = new Connect();
    Queries queries = new Queries();

    Connection connection = connect.loadConnection();

    setTemperatureTable(queries.getTemperatureBySector(connection, sectorID));
    setSizeTemperatureTable(getTemperatureTable().size());
    setLastTemperatureData(getTemperatureTable().get(getSizeTemperatureTable() - 1));
    setAverageTemperature(calculateAverageTemperature());
  }

  public void fetchHumidityReadingBySector(int sectorID) {

    // Queries and returns all humidity-data from a given sector [see class Query].

    Connect connect = new Connect();
    Queries queries = new Queries();

    Connection connection = connect.loadConnection();

    setHumidityTable(queries.getHumidityBySector(connection, sectorID));
    setSizeHumidityTable(getHumidityTable().size());
    setLastHumidityData(getHumidityTable().get(getSizeHumidityTable() - 1));
    setAverageHumidity(calculateAverageHumidity());
  }

  private float calculateAverageTemperature() {

    Calculator calculator = new Calculator();
    List<Float> readings = new ArrayList<>();

    for (SensorData data : getTemperatureTable())
      readings.add(data.getData());

    return calculator.calculateAverage(readings);
  }

  private float calculateAverageHumidity() {

    Calculator calculator = new Calculator();
    List<Float> readings = new ArrayList<>();

    for (SensorData data : getHumidityTable())
      readings.add(data.getData());

    return calculator.calculateAverage(readings);
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

// Queries and returns all humidity-data from a given sector [see class Query].
// Queries given sector's temperature-data, calculates average and returns it [see class Query, Calculator].
// Queries and returns all temperature-data from a given sector [see class Query].