package com.example.greenhouse.repositories.repoutils;

import com.example.greenhouse.models.Sector;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.models.modelutils.SensorData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
//Tanken med denna Query-klass var att öka abstraktionsnivån en aning och göra koden mer överskådlig men kanske att
//den raka motsatsen istället uppnåtts! Väl på plats så fick den dock bero.

  private static final String SECTORS = "SELECT * FROM sector";
  // Förfrågan om all data från tabellen sector.

  private static final String ENERGY_DATA = "SELECT * FROM energy_data";
  // Förfrågan om all data från tabellen energy_data.

  private static final String TEMP_BY_SECTOR = "SELECT * FROM sensor_data WHERE sensor_id = 1 AND sector_id = ?";
  // Förfrågan om all data från tabellen sensor_data som har en FK pekandes mot en TEMPERATURE-sensor (1) och
  // sektor ? (i detta fall ett värde som sätts av användarens knappklick - se temperature.html).

  private static final String HUMID_BY_SECTOR = "SELECT * FROM sensor_data WHERE sensor_id = 2 AND sector_id = ?";
  // Förfrågan om all data från tabellen sensor_data som har en FK pekandes mot en HUMIDITY-sensor (1) och
  // sektor ? (i detta fall ett värde som sätts av användarens knappklick - se temperature.html).

  private static final String ALL_SENSOR_DATA = "SELECT * FROM sensor_data";

  public Queries() {}

  public List<SensorData> getTemperatureBySector(Connection connection, int sectorID) {
    // Connection-object och sectorID som parameter som vid lyckad query skapar ett resultset innehållandes en
    // givens sektors temperature-data. Detta returneras i en lista som skapas i methoden "createSensorTable" som
    // återfinns längre ned.

    try {
      PreparedStatement statement = connection.prepareStatement(TEMP_BY_SECTOR);
      statement.setInt(1, sectorID);
      ResultSet resultset = statement.executeQuery();
      return createSensorTable(resultset);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }

  public List<SensorData> getHumidityBySector(Connection connection, int sectorID) {
    // Connection-object och sectorID som parameter som vid lyckad query skapar ett resultset innehållandes en
    // givens sektors humidity-data. Detta returneras i en lista som skapas i methoden "createSensorTable" som
    // återfinns längre ned.

    try {
      PreparedStatement statement = connection.prepareStatement(HUMID_BY_SECTOR);
      statement.setInt(1, sectorID);
      ResultSet resultset = statement.executeQuery();
      return createSensorTable(resultset);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }

  public List<Sector> getSectorTable(Connection connection) {
    // Connection-object som parameter som vid lyckad query skapar ett resultset innehållandes samtliga sektorer.
    // Detta returneras i en lista som skapas i methoden "createSectorTable" som återfinns längre ned.

    try {
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(SECTORS);
      return createSectorTable(resultset);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }
  public List<EnergyData> getEnergyTable(Connection connection) {
    // Connection-object som parameter som vid lyckad query skapar ett resultset innehållandes samtlig "energy_data".
    // Detta returneras i en lista som skapas i methoden "createEnergyTable" som återfinns längre ned.

    try {
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(Queries.ENERGY_DATA);
      return createEnergyTable(resultset);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }

  private List<EnergyData> createEnergyTable(ResultSet resultset) {
    // Drivar-method. resultSet-object som parameter som bygger och returnerar en lista - "table" enligt "önskad query".

    List<EnergyData> energyTable= new ArrayList<>();

    try {
      while (resultset.next()) {
        int id = resultset.getInt("id");
        float price = resultset.getFloat("price");
        Timestamp timestamp = resultset.getTimestamp("timestamp");
        energyTable.add(new EnergyData(id, price, timestamp));
      }

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
    return energyTable;
  }

  private List<SensorData> createSensorTable(ResultSet resultset) {
    // Drivar-method. resultSet-object som parameter som bygger och returnerar en lista - "table" enligt "önskad query".

    List<SensorData> sensorTable= new ArrayList<>();

    try {
      while (resultset.next()) {
        int id = resultset.getInt("id");
        float data = resultset.getFloat("data");
        Timestamp timestamp = resultset.getTimestamp("timestamp");
        int sector_id = resultset.getInt("sector_id");
        int sensor_id = resultset.getInt("sensor_id");
        sensorTable.add(new SensorData(id, data, timestamp, sector_id, sensor_id));
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
    return sensorTable;
  }

  private List<Sector> createSectorTable(ResultSet resultset) {
    // Drivar-method. resultSet-object som parameter som bygger och returnerar en lista - "table" enligt "önskad query".

    List<Sector> sectorTable = new ArrayList<>();

    try {
      while (resultset.next()) {
        int id = resultset.getInt("id");
        String name = resultset.getString("name");
        sectorTable.add(new Sector(id, name));
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
    return sectorTable;
  }
}
