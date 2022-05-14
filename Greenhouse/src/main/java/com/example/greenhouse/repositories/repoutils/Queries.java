package com.example.greenhouse.repositories.repoutils;

import com.example.greenhouse.models.Sector;
import com.example.greenhouse.models.modelutils.EnergyData;
import com.example.greenhouse.models.modelutils.SensorData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {

  // static SQL querys-strings.

  public static final String ALL_SECTORS = "SELECT * FROM sector";
  public static final String ENERGY_DATA = "SELECT * FROM energy_data";
  public static final String TEMP_BY_SECTOR = "SELECT * FROM sensor_data WHERE sensor_id = 1 AND sector_id = ?";
  public static final String HUMID_BY_SECTOR = "SELECT * FROM sensor_data WHERE sensor_id = 2 AND sector_id = ?";

  public Queries() {}

  public List<SensorData> getTemperatureBySector(Connection connection, int sectorID) {

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

    try {
      Statement statement = connection.createStatement();
      ResultSet resultset = statement.executeQuery(ALL_SECTORS);
      return createSectorTable(resultset);

    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
      return null;
    }
  }
  public List<EnergyData> getEnergyTable(Connection connection) {

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
