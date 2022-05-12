package com.example.greenhouse.models.modelutils;

import java.sql.Timestamp;

public class SensorData {

  private int id;
  private float data;
  private Timestamp timestamp;
  private int sectorID;
  private int sensorID;
  public SensorData() {}

  public SensorData(int id, float data, Timestamp timestamp, int sectorID, int sensorID) {
    this.id = id;
    this.data = data;
    this.timestamp = timestamp;
    this.sectorID = sectorID;
    this.sensorID = sensorID;
  }

  public int getId() {return id;}
  public float getData() {return data;}
  public Timestamp getTimestamp() {return timestamp;}
  public int getSectorID() {return sectorID;}
  public int getSensorID() {return sensorID;}

  public void setId(int id) {this.id = id;}
  public void setData(float data) {this.data = data;}
  public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}
  public void setSectorID(int sectorID) {this.sectorID = sectorID;}
  public void setSensorID(int sensorID) {this.sensorID = sensorID;}

}
