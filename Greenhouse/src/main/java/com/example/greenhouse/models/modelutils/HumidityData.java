package com.example.greenhouse.models.modelutils;

public class HumidityData {

  private int id;
  private float data;
  private String timestamp;

  public HumidityData() {}

  public HumidityData(int id, float data, String timestamp) {
    this.id = id;
    this.data = data;
    this.timestamp = timestamp;
  }

  public int getId() {return id;}
  public float getData() {return data;}
  public String getTimestamp() {return timestamp;}

  public void setId(int id) {this.id = id;}
  public void setData(float data) {this.data = data;}
  public void setTimestamp(String timestamp) {this.timestamp = timestamp;}
}
