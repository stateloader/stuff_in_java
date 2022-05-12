package com.example.greenhouse.models;

public class Sensor {

  private int id;
  private String sensorType;

  // constructors
  public Sensor() {}

  public Sensor(int id, String sensorType) {
    this.id = id;
    this.sensorType = sensorType;
  }

  // getters
  public String getSensorType() {return sensorType;}
  public int getId() {return id;}

  // setters
  public void setId(int id) {this.id = id;}
  public void setSensorType(String sensorType) {this.sensorType = sensorType;}
}
