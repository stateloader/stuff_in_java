package com.example.greenhouse.repositories.repoutils;

public class Queries {

  public static final String ALL_SECTORS =
          "SELECT * FROM sector";
  public static final String ALL_ENERGY_DATA =
          "SELECT * FROM energy_data";
  public static final String ADD_ENERGY_DATA =
          "INSERT INTO energy_data (price, timestamp) VALUES (?, NOW())";
  public static final String ALL_SENSOR =
          "SELECT * FROM sensor_data";
  public static final String ALL_TEMP =
          "SELECT * FROM sensor_data WHERE sensor_id = 1";
  public static final String ALL_HUMID =
          "SELECT * FROM sensor_data WHERE sensor_id = 2";
  public static final String ALL_TEMP_BY_SECTOR =
          "SELECT * FROM sensor_data WHERE sensor_id = 1 AND sector_id = ?";
  public static final String ALL_HUMID_BY_SECTOR =
          "SELECT * FROM sensor_data WHERE sensor_id = 2 AND sector_id = ?";

}
