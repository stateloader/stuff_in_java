package com.example.greenhouse.repositories;

public class SensorRepository {

  private static final String QUERY_ALL = "select * from sensor_temp";
  private static final String QUERY_ADD = "insert into sensor_temp (sector_id, device_name) values (?, ?)";
}

