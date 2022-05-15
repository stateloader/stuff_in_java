package com.example.greenhouse.repositories;

import com.example.greenhouse.models.modelutils.Calculator;
import com.example.greenhouse.models.modelutils.SensorData;
import com.example.greenhouse.repositories.repoutils.Connect;
import com.example.greenhouse.repositories.repoutils.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SensorRepository {
//Repository/DAO som hanterar query- och datahanteringslogik rörande DB:ns "sensor_data"-tabell. I databasen så pekar
//denna tabell medelst 2 foreign key mot endera en given sensor-typ (humidity/temperature) och en given sektor. Skulle
//tro att sättet jag byggt upp denna klass är väldigt långt från best practice men det fungerar, dock =)

  // Storlek temperature- och humidity-värden i sensor_data-tabellen.
  private int sizeTemperatureTable;
  private int sizeHumidityTable;

  // Medelvärdet av inlästa temperature -och humidity-värden i sensor_data_tabellen.
  private float averageTemperature;
  private float averageHumidity;

  // Listor inom vilka inlästa temperature -och humidity-värden sparas.
  private List<SensorData> temperatureTable;
  private List<SensorData> humidityTable;

  // "Placeholders" för senaste temperature -och sensor-värde.
  private SensorData lastTemperatureData;
  private SensorData lastHumidityData;

  public SensorRepository() {}

  // getters.
  public int getSizeTemperatureTable() {return sizeTemperatureTable;}
  public int getSizeHumidityTable() {return sizeHumidityTable;}
  public float getAverageTemperature() {return averageTemperature;}
  public float getAverageHumidity() {return averageHumidity;}
  public List<SensorData> getTemperatureTable() {return temperatureTable;}
  public List<SensorData> getHumidityTable() {return humidityTable;}
  public SensorData getLastTemperatureData() {return lastTemperatureData;}
  public SensorData getLastHumidityData() {return lastHumidityData;}

  // setters.
  public void setSizeTemperatureTable(int sizeTemperatureTable) {this.sizeTemperatureTable = sizeTemperatureTable;}
  public void setSizeHumidityTable(int sizeHumidityTable) {this.sizeHumidityTable = sizeHumidityTable;}
  public void setAverageTemperature(float averageTemperature) {this.averageTemperature = averageTemperature;}
  public void setAverageHumidity(float averageHumidity) {this.averageHumidity = averageHumidity;}
  public void setTemperatureTable(List<SensorData> temperatureTable) {this.temperatureTable = temperatureTable;}
  public void setHumidityTable(List<SensorData> humidityTable) {this.humidityTable = humidityTable;}
  public void setLastTemperatureData(SensorData lastTemperatureData) {this.lastTemperatureData = lastTemperatureData;}
  public void setLastHumidityData(SensorData lastHumidityData) {this.lastHumidityData = lastHumidityData;}

  public void fetchTemperatureReadingBySector(int sectorID) {
    // Läser in en given sektors temperature-värden.

    // Klasserna Connect och Queries återfinns och förklaras i sub-paket "repoutils".
    Connect connect = new Connect();
    Queries queries = new Queries();

    // Connection upprättas före en lista skapas av givna sektorns temperature-sensor-värden.
    Connection connection = connect.connectDatabase();
    setTemperatureTable(queries.getTemperatureBySector(connection, sectorID));

    // Tabellens storlek, senaste (tillagda) data och medelvärde "setts".
    setSizeTemperatureTable(getTemperatureTable().size());
    setLastTemperatureData(getTemperatureTable().get(getSizeTemperatureTable() - 1));
    setAverageTemperature(calculateAverageTemperature());
  }

  public void fetchHumidityReadingBySector(int sectorID) {
    // Läser in en given sektors humidity-värden.

    // Klasserna Connect och Queries återfinns och förklaras i sub-paket "repoutils".
    Connect connect = new Connect();
    Queries queries = new Queries();

    // Connection upprättas före en lista skapas av givna sektorns humidity-sensor-värden.
    Connection connection = connect.connectDatabase();
    setHumidityTable(queries.getHumidityBySector(connection, sectorID));

    // Tabellens storlek, senast (tillagda) instans och medelvärde "setts".
    setSizeHumidityTable(getHumidityTable().size());
    setLastHumidityData(getHumidityTable().get(getSizeHumidityTable() - 1));
    setAverageHumidity(calculateAverageHumidity());
  }

  private float calculateAverageTemperature() {
    // Logik för medelvärdesberäkning av given sektors uppmätta temperature-värden.

    Calculator calculator = new Calculator();
    List<Float> readings = new ArrayList<>();
    for (SensorData data : getTemperatureTable())
      readings.add(data.getData());
    return calculator.calculateAverage(readings);
  }

  private float calculateAverageHumidity() {
    // Logik för medelvärdesberäkning av given sektors uppmätta humidity-värden.

    Calculator calculator = new Calculator();
    List<Float> readings = new ArrayList<>();
    for (SensorData data : getHumidityTable())
      readings.add(data.getData());
    return calculator.calculateAverage(readings);
  }

  public String getSectorName(int sectorID) {
    // Kalle-Anka method som för nu bryter mot all den skalbarhet jag försökt ro i land.

    switch(sectorID) {
      case 1: return "SECTOR A";
      case 2: return "SECTOR B";
      case 3: return "SECTOR C";
      default: return "NO SECTOR";
    }
  }
}